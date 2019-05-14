package com.github.liaojiacan.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liaojiacan
 * @date 2019/3/19
 */
public class NioServer implements Runnable {


	private Selector selector;
	private ServerSocketChannel servChannel;
	private volatile boolean stop;


	public NioServer(int port) {

		try {
			selector = Selector.open();
			servChannel = ServerSocketChannel.open();
			servChannel.configureBlocking(false);
			servChannel.socket().bind(new InetSocketAddress(port));
			// 注册Accept 事件
			servChannel.register(selector,SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		NioServer server = new NioServer(8080);
		new Thread(server,"NIO-SERVER").start();
	}

	@Override
	public void run() {
		while (!stop){
			try {
				// 设置超时机制，避免空循环。
				selector.select(1000);
				Set<SelectionKey> selectKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectKeys.iterator();
				while (it.hasNext()){
					SelectionKey key = it.next();
					handleInput(key);
				}
				selectKeys.clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()){
			if(key.isAcceptable()){
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector,SelectionKey.OP_READ);
				return;
			}

			if(key.isReadable()){
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readerBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readerBuffer);
				if(readBytes > 0){
					readerBuffer.flip();
					byte[] bytes = new byte[readerBuffer.remaining()];
					readerBuffer.get(bytes);
					readerBuffer.clear();
					String body = new String(bytes,"UTF-8");
					System.out.println("request:"+body);
					byte[] responseBody = ("response:"+body).getBytes();
					ByteBuffer writeBuffer = ByteBuffer.allocate(responseBody.length);
					writeBuffer.put(responseBody);
					writeBuffer.flip();
					sc.write(writeBuffer);
					writeBuffer.clear();
				}else if(readBytes<0){
					// 对方链路关闭
					System.out.println("Client closed");
					key.cancel();
					sc.close();
				}else{

				}
			}
		}
	}
}
