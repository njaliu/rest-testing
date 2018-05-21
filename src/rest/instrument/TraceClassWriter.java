package rest.instrument;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class TraceClassWriter extends ClassWriter{
	public ClassLoader loader;
	public TraceClassWriter(ClassReader classReader, int flags, ClassLoader loader) {  
        super(classReader, flags);  
        this.loader = loader;  
    }  
  
    protected String getCommonSuperClass(final String type1, final String type2) {  
        Class c, d;  
        try {  
            c = Class.forName(type1.replace('/', '.'), true, loader);  
            d = Class.forName(type2.replace('/', '.'), true, loader);  
        } catch (Exception e) {  
            throw new RuntimeException(e.toString());  
        }  
        if (c.isAssignableFrom(d)) {  
            return type1;  
        }  
        if (d.isAssignableFrom(c)) {  
            return type2;  
        }  
        if (c.isInterface() || d.isInterface()) {  
            return "java/lang/Object";  
        } else {  
            do {  
                c = c.getSuperclass();  
            } while (!c.isAssignableFrom(d));  
            return c.getName().replace('.', '/');  
        }  
    }  
}
