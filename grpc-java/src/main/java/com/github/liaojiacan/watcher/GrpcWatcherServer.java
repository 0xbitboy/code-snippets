package com.github.liaojiacan.watcher;

import com.gitbub.liaojiacan.watcher.HelloReply;
import com.gitbub.liaojiacan.watcher.RegisterRequest;
import com.gitbub.liaojiacan.watcher.WatcherGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @author liaojiacan
 * @date 2019/4/10
 */
public class GrpcWatcherServer extends WatcherGrpc.WatcherImplBase {



	private static final Logger logger = Logger.getLogger(GrpcWatcherServer.class.getName());

	private Server server;

	private void start() throws IOException {
		/* The port on which the server should run */
		int port = 50051;
		server = ServerBuilder.forPort(port)
				.addService(this)
				.build()
				.start();
		logger.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				GrpcWatcherServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon threads.
	 */
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	/**
	 * Main launches the server from the command line.
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		final GrpcWatcherServer server = new GrpcWatcherServer();
		server.start();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()){
			String message = scanner.nextLine();
			server.notifyClient(message);
		}

	}

	private Map<String,StreamObserver<HelloReply> > observerMap = new ConcurrentHashMap<>();

	@Override
	public void sayHello(RegisterRequest request, StreamObserver<HelloReply> responseObserver) {
		observerMap.put(request.getName(),responseObserver);
	}

	public void notifyClient(String message){
		final  HelloReply reply = HelloReply.newBuilder().setMessage(message).build();
		List<String> disconnectClients = new ArrayList<>();
		observerMap.forEach((clientId,responseObserver)->{
			try{
				responseObserver.onNext(reply);
			}catch (Throwable e){
				disconnectClients.add(clientId);
			}
		});
		disconnectClients.forEach(clientId->observerMap.remove(clientId));
	}

}
