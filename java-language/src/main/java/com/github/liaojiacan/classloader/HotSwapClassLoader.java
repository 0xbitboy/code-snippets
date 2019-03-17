package com.github.liaojiacan.classloader;

import java.io.*;

/**
 * @author liaojiacan
 * @date 2019/3/16
 */
public class HotSwapClassLoader extends ClassLoader {

	/**
	 * 指定目录下的类可以热加载
	 */
	private String basePath;

	public HotSwapClassLoader(String basePath) {
		this.basePath = basePath;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		// 加载指定目录下的class
		if (c == null) {
			try {
				c = findClass(name);
				if (c != null) {
					return c;
				}
			} catch (ClassNotFoundException e) {
				return super.loadClass(name);
			}

		}
		return super.loadClass(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		String classResourcePath = this.basePath + "/" + name.replaceAll("\\.", "/");

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(classResourcePath));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int len;
			byte[] buffer = new byte[1024];
			while ((len = fileInputStream.read(buffer)) > 0) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
			byte[] bytes = byteArrayOutputStream.toByteArray();
			return defineClass(name, bytes, 0, bytes.length);
		} catch (IOException e) {
			throw new ClassNotFoundException(name);
		}

	}
}
