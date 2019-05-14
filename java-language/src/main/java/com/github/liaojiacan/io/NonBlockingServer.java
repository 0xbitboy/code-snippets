package com.github.liaojiacan.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liaojiacan
 * @date 2019/3/18
 */
public class NonBlockingServer {

	static  class Request{
		private InputStream inputStream;

		public Request(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public InputStream getInputStream() {
			return inputStream;
		}
	}

	static class Response{
		private OutputStream outputStream;

		public Response(OutputStream outputStream) {
			this.outputStream = outputStream;
		}

		public OutputStream getOutputStream() {
			return outputStream;
		}
	}

	abstract static class Handler implements Runnable {
		private Request request;
		private Response response;

		public Handler(Request request, Response response) {
			this.request = request;
			this.response = response;
		}

		@Override
		public void run() {
			doService(request,response);
		}

		abstract  void doService(Request request, Response response);
	}

	public static void main(String[] args) throws IOException {
		ExecutorService executeService = Executors.newFixedThreadPool(10);
		ServerSocket serverSocket = new ServerSocket(8080);

		// 虽然可以处理多个客户端了，但是需要给每个线程分配一个线程池, 线程池满了还是会阻塞
		// 这种模式在并发量小的情况下是没有问题的
		// 但是如果你的并发量大，连接数又多，不可能创建一个很大的线程池去处理。
		for(;;){
			// accept 这里还是阻塞的
			Socket socket = serverSocket.accept();
			executeService.execute(new Handler(new Request(socket.getInputStream()),new Response(socket.getOutputStream())){
				@Override
				public void doService(Request request, Response response) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
					try {
						String line ;
						// telnet 客户端 退出指令
						// read 的操作也是阻塞的，也就是说如果客户端传的慢，这里也会导致阻塞。
						while (!"^]".equals(line=bufferedReader.readLine())){
							System.out.println(line);
						}
						// write 操作也是阻塞的，传输完后才会退出。
						response.getOutputStream().write("OK".getBytes());
						response.getOutputStream().flush();
						response.getOutputStream().close();

						// Socket 面相的是IO，也就是我们工作线程处理的就是IO流，而IO是阻塞的，也就导致我们的一个线程在处理IO的时候，不能切换出来处理其他任务，只能干等，因为不知道数据什么时候来。
					}catch (Exception e){

					}finally {
						try {
							bufferedReader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			});


		}

	}
}
