package com.github.liaojiacan.watcher;

import com.gitbub.liaojiacan.watcher.HelloReply;
import com.gitbub.liaojiacan.watcher.RegisterRequest;
import com.gitbub.liaojiacan.watcher.WatcherGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GrpcWatcherClient {


	private static final Logger logger = Logger.getLogger(GrpcWatcherClient.class.getName());

	private final ManagedChannel channel;
	private final WatcherGrpc.WatcherStub watcherStub ;


	public GrpcWatcherClient(String host, int port) {
		this(ManagedChannelBuilder.forAddress(host, port)
				// Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
				// needing certificates.
				.usePlaintext(true)
				.build());
	}

	GrpcWatcherClient(ManagedChannel channel) {
		this.channel = channel;
		watcherStub = WatcherGrpc.newStub(channel);
	}


	private void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	private void watching(String clientId) {

		RegisterRequest request = RegisterRequest.newBuilder().setName(clientId).build();

		StreamObserver<HelloReply> responseObserver = new StreamObserver<HelloReply>() {
			@Override
			public void onNext(HelloReply helloReply) {
				logger.info("Receive Message:"+helloReply.getMessage());
			}

			@Override
			public void onError(Throwable throwable) {
				// 这里5s后从新连接
				watcherStub.sayHello(request,this);
			//	throwable.printStackTrace();//
			}

			@Override
			public void onCompleted() {
				logger.info("Server disconnect");
			}
		};
		watcherStub.sayHello(request,responseObserver);

	}




	public static void main(String[] args) throws Exception {
		GrpcWatcherClient client = new GrpcWatcherClient("localhost", 50051);
		try {
         /* Access a service running on the local machine on port 50051 */
			String clientId = UUID.randomUUID().toString();
			client.watching(clientId);
			Thread.sleep(TimeUnit.MINUTES.toMillis(10086));
		} finally {
			client.shutdown();
		}
	}


}
