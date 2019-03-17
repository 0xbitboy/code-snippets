package com.github.liaojiacan.classloader;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author liaojiacan
 * @date 2019/3/17
 */
public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		new Bootstrap("/Users/liaojiacan/Workspace/java/personal/code-snippets/java-language/target/classes"
				,"com.github.liaojiacan.classloader.app.Application").boot();
	}
}
