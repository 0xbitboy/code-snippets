package com.github.liaojiacan.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author liaojiacan
 * @date 2019/2/25
 */
public class UnsafeUtil {

	public static Unsafe getUnsafe(){
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			return (Unsafe)f.get(null);
		}catch (Throwable tr){
			return  null;
		}
	}

}
