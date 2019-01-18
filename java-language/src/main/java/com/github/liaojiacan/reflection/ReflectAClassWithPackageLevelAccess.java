package com.github.liaojiacan.reflection;

import java.lang.reflect.Field;

/**
 * @author liaojiacan
 * @date 2019/1/18
 */
public class ReflectAClassWithPackageLevelAccess {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

		Class<?> clazz = Class.forName("com.github.liaojiacan.reflection.pkg1.PackageLevelAccessClass");
		Field field = clazz.getField("OBJ");
		field.setAccessible(true);
		System.out.println(field.get(null));
		System.out.println(new Object() == new Object());
	}
}
