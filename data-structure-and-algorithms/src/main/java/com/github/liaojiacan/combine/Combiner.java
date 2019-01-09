package com.github.liaojiacan.combine;

import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表的组合
 * @see <a href="https://leetcode-cn.com/problems/combinations/submissions/">leetcode-Combinations</a>
 * @author liaojiacan
 * @date 2019/1/9
 */
public class Combiner {

	/**
	 * 获取originList的 大小为combinationSize的T元素的所有组合
	 * @param originList
	 * @param combinationSize
	 * @param <T>
	 * @return
	 */
	public static <T> List<List<T>> combine(List<T> originList, int combinationSize){
		Assert.check(originList.size() >=combinationSize,"originList size should greater than combinationSize");
		if(originList.size() == combinationSize ){
			List<List<T>> result  = new ArrayList<>(1);
			result.add(originList);
			return result;
		}
		List<List<T>> combinations = new ArrayList<>(calculateCombinationNum(originList.size(),combinationSize));
		combine(combinations,originList,combinationSize,0,new ArrayList<>(combinationSize));
		return combinations;
	}


	private static <T> void combine(List<List<T>> combinations,List<T> originList,int combinationSize,int elementIndex,List<T> combination){

		if( combinationSize == 0 ){
			combinations.add(new ArrayList<>(combination));
			return;
		}

		for( int i = elementIndex ; i <= originList.size()-combinationSize ; i++ ){
			combination.add(originList.get(i));
			combine(combinations,originList,combinationSize-1,i+1,combination);
			combination.remove(combination.size()-1);
		}

	}

	/**
	 * 计算组合的近视值 C(m,n)
	 * @see <a href="https://program-dog.blogspot.com/2017/05/stirlingapproximation.html">stirling</a>
	 * @see <a href="https://blog.csdn.net/uself/article/details/54575930">组合的近似值算法</a>
	 * @param originElementSize
	 * @param combinationSize
	 * @return
	 */
	private static int calculateCombinationNum(int originElementSize,int combinationSize){
		int m = originElementSize;
		int n = combinationSize;
		return (int) Math.ceil((1/Math.sqrt(2*Math.PI))*Math.sqrt(m/(n*(m-n)))*(Math.pow(m,m)/Math.pow(n,n))*Math.pow((m-n),(n-m)));
	}

}
