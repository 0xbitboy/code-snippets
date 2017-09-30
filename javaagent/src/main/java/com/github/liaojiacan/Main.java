package com.github.liaojiacan;

import com.github.liaojiacan.transformer.IedisTransformer;
import com.github.liaojiacan.transformer.InjectPrintTransformer;
import com.github.liaojiacan.transformer.SimpleTransformer;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.instrument.Instrumentation;


public class Main {
	public static void premain(String agentOps, Instrumentation inst) {
		PrintStream out = null;
		try {
			out = new PrintStream("/tmp/system.out");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(out);
		System.setErr(out);

		if ("iedis".equals(agentOps)){
			inst.addTransformer(new IedisTransformer());
		}else if("injectPrint".equals(agentOps)) {
			inst.addTransformer(new InjectPrintTransformer());
		}else {
			inst.addTransformer(new SimpleTransformer());
		}
	}

	public static void main(String[] args) {
		System.out.println(helloWorld());
	}

	public static String helloWorld(){
		return "This is a javaagent!";
	}
}
