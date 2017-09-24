package com.github.liaojiacan;

import com.github.liaojiacan.transformer.SimpleTransformer;

import java.lang.instrument.Instrumentation;

public class Main {
	public static void premain(String agentOps, Instrumentation inst) {
		inst.addTransformer(new SimpleTransformer());
	}

	public static void main(String[] args) {
		System.out.println("This is a javaagent!");
	}
}
