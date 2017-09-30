package com.github.liaojiacan.transformer;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

/**
 * 在返回值是String 的函数 中插入print语句打印出来
 * 可以把一些混淆字符串加密方法 通过动态的方法定位到字符串的位置。
 */
public class InjectPrintTransformer implements ClassFileTransformer {


	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		try {
			CtClass clazz = ClassPool.getDefault().makeClass(new ByteArrayInputStream(classfileBuffer));
			CtMethod[] mds = clazz.getMethods();
			CtClass stringClass = ClassPool.getDefault().getCtClass(String.class.getName());
			for(CtMethod md:mds){
				if(md.getReturnType().equals(stringClass)){
					String name = md.getLongName();
					md.insertAfter("System.out.println(\""+name+"\"); System.out.println(\"return:\"+$_);");
				}
			}
			return  clazz.toBytecode();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
		return null;
	}
}
