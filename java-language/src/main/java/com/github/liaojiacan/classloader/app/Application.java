package com.github.liaojiacan.classloader.app;

/**
 * @author liaojiacan
 * @date 2019/3/17
 */
public class Application {

	public Integer version = 47;

	/**
	 * 应用的启动入口
	 */
	public void start() {
		System.out.println("Start... version=" + version);
	}

	/**
	 * 应用的停止入口
	 */
	public void stop() {
		System.out.println("Stop... version=" + version);
	}
}
