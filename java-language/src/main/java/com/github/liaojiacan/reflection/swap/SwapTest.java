package com.github.liaojiacan.reflection.swap;

import com.github.liaojiacan.classinit.A;

import java.lang.reflect.Field;

/**
 * @author liaojiacan
 * @date 2019/4/10
 */
public class SwapTest {



	public static void main(String[] args) {

		Integer a = 1 ;
		Integer b = 2 ;

		System.out.printf("Before: a=%d , b=%d \n",a,b);
		swap(a,b);
		System.out.printf("After: a=%d , b=%d\n",a,b);
		Integer c = 1;
		System.out.println(c);

	}


	public static  void swap(Integer aNum,Integer bNum){

		try {
			int a = aNum , b = bNum;
			Field A = aNum.getClass().getDeclaredField("value");
			A.setAccessible(true);
			A.set(aNum,b);

			Field B = bNum.getClass().getDeclaredField("value");
			B.setAccessible(true);
			B.set(bNum,new Integer(a));


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
