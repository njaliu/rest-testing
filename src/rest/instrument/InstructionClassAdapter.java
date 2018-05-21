package rest.instrument;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class InstructionClassAdapter extends ClassVisitor{
	String cname;
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, 
	      String signature, String[] exceptions) {
	    MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
	  
	    if((access & Opcodes.ACC_SYNCHRONIZED) != 0 ){
	    		GlobalStateForInstrumentation.methodTosyn.put(cname+":"+name, true); //cname+methodName 不足够唯一标记一个方法
	    }
	  
	    if (mv != null) {
	      return new InstructionMethodAdapter(mv, cname, name.equals("<init>"), 
	         GlobalStateForInstrumentation.instance);
	    }
	    return null;
	}
	  
	public InstructionClassAdapter(ClassVisitor cv) {
	    super(Opcodes.ASM5, cv);
	}
	
	@Override
	public void visit(int version,
	          int access,
	          String name,
	          String signature,
	          String superName,
	          String[] interfaces){		  
		  cname = name;		 
		  cv.visit(version, access, name, signature, superName, interfaces);		  
	}
	
}
