package com.github.liaojiacan.classloader;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * @author liaojiacan
 * @date 2019/3/16
 */
public class Bootstrap {

	private String basePath;
	private Object application;
	private String applicationClassName;
	private volatile ClassLoader applicationClassLoader;


	public Bootstrap(String basePath, String applicationClassName) {
		this.basePath = basePath;
		this.applicationClassLoader = new HotSwapClassLoader(this.basePath);
		this.applicationClassName = applicationClassName;
		try {
			this.application = getApplication();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void boot() {
		try {
			startApplication();
		} catch (Exception e) {
			e.printStackTrace();
		}
		registerResourceWatcher();
	}

	private Object getApplication() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return this.applicationClassLoader.loadClass(this.applicationClassName).newInstance();
	}

	private void startApplication() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		this.application.getClass().getDeclaredMethod("start", null).invoke(this.application, new Object[0]);
	}

	private void stopApplication() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		this.application.getClass().getDeclaredMethod("stop", null).invoke(this.application, new Object[0]);
	}

	public ClassLoader getApplicationClassLoader() {
		return applicationClassLoader;
	}

	public void setApplicationClassLoader(ClassLoader applicationClassLoader) {
		this.applicationClassLoader = applicationClassLoader;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		stopApplication();
	}

	private void registerResourceWatcher() {
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path p = Paths.get(basePath);
			p.register(watchService, new WatchEvent.Kind[]{ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE});
			while (true) {
				WatchKey k = watchService.take();
				for (WatchEvent<?> e : k.pollEvents()) {
					reloadApplication();
					break;
				}
				k.reset();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void reloadApplication() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
		stopApplication();
		this.applicationClassLoader = new HotSwapClassLoader(basePath);
		this.application = getApplication();
		startApplication();
	}

}
