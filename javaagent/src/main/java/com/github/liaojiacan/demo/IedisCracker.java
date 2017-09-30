package com.github.liaojiacan.demo;

import javassist.*;

import java.io.IOException;

public class IedisCracker {

	private final static String IDEA_LIB="/Applications/IntelliJ IDEA.app/Contents/lib/*";
	private final static String IDEIS_LIB="/Users/liaojiacan/Library/Application Support/IntelliJIdea2017.2/Iedis/lib/*";

	public static void main(String[] args) {
		try {
			ClassPool.getDefault().appendClassPath(IDEA_LIB);
			ClassPool.getDefault().appendClassPath(IDEIS_LIB);

			CtClass clazz = ClassPool.getDefault().getCtClass("com.seventh7.widget.iedis.a.p");

			CtMethod[] mds = clazz.getDeclaredMethods();
			for(CtMethod method : mds){
				if(method.getLongName().startsWith("com.seventh7.widget.iedis.a.p.f")){
					System.out.println("Inject :: SUCCESS!");
					try {
						method.insertBefore("if(true){return true;} ");
					} catch (CannotCompileException e) {
						e.printStackTrace();
					}
					continue;
				}
			}

			clazz.writeFile("/tmp/p.class");

		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
