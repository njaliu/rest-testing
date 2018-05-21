package rest.instrument;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;


public class InstructionTransformer implements ClassFileTransformer {
	private boolean writeInstrumentedClasses = true;
	private String instDir = "instrumented";
	
	@SuppressWarnings("unused")
	public static void premain(String agentArgs, Instrumentation inst) {
	    inst.addTransformer(new InstructionTransformer());
	}
	
	@Override
	public byte[] transform(ClassLoader loader, String cname, Class<?> classBeingRedefined,
	      ProtectionDomain d, byte[] cbuf)
	    throws IllegalClassFormatException {
		 if (classBeingRedefined != null) {
		      return cbuf;
		    }
		 //排除不需要插桩的类
		 
		// System.out.println("###transformer:"+loader+":"+cname);
		  
		 
		 boolean toInstrument = !shouldExclude(cname);
		// boolean toInstrument = true;
		 //输入json文件
		 if(GlobalStateForInstrumentation.instance.getJb() == null) {
			 GlobalStateForInstrumentation.instance.inputJson();
		 }
		 
		 if (toInstrument) {
		      ClassReader cr = new ClassReader(cbuf);
		      ClassWriter cw = new ClassWriter(cr, 0);
		      ClassVisitor cv = new InstructionClassAdapter(cw);
	
		      try {
		        cr.accept(cv, 0);
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		      byte[] ret = cw.toByteArray();
		      if (writeInstrumentedClasses) {
		        try {
		          File file = new File(instDir + "/" + cname + ".class");
		          File parent = new File(file.getParent());
		          parent.mkdirs();
		          FileOutputStream out = new FileOutputStream(file);
		          out.write(ret);
		          out.close();
		        } catch(Exception e) {
		          e.printStackTrace();
		        }
		      }
		     // System.out.println("###transform: the end!!!");
		      return ret;
		 }else {
		 	//  System.out.println("###transform: the end!!!");
		      return cbuf;
		 }
	}
	 
	private static boolean shouldExclude(String cname) {
	    String[] exclude = {"com/sun", "sun", "java", "jdk", 
	               			"rest"};
	    for (String e : exclude) {
	      if (cname.startsWith(e)) {
	        return true;
	      }
	    }
	    return false;
	}
}
