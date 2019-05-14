package com.github.liaojiacan.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liaojiacan
 * @date 2019/3/18
 */
public class BlockedServer {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(8080);
		// 一次只能处理一个客户端
		for(;;){
			Socket socket = serverSocket.accept();
			InputStream ips = socket.getInputStream();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ips));
			String line ;
			while (!"^]".equals(line=bufferedReader.readLine())){
				System.out.println(line);
			}
			socket.getOutputStream().write("OK".getBytes());
			socket.getOutputStream().flush();
			socket.getOutputStream().close();
			bufferedReader.close();
			socket.close();
		}

	}
}
