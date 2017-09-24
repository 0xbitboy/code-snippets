# Javaagent

  可以对需要运行的class进行hack

### 1.实现一个ClassFileTransformer

```
public class SimpleTransformer implements ClassFileTransformer {


	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		System.out.println(className);
		System.out.println(protectionDomain.toString());
		return new byte[0];
	}
}
```

### 2.实现一个Premain-Class

```

public class Main {
	public static void premain(String agentOps, Instrumentation inst) {
		inst.addTransformer(new SimpleTransformer());
	}

	public static void main(String[] args) {
		System.out.println("This is a javaagent!");
	}
}

```

### 3.MANIFEST.MF配置

```
Manifest-Version: 1.0
Premain-Class: com.github.liaojiacan.Main
Can-Redefine-Classes: true
Can-Retransform-Classes: true
Can-Set-Native-Method-Prefix: true
```

### 4.运行命令

```
java -javaagent:agent.jar -jar app.jar
```
