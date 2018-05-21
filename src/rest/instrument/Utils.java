package rest.instrument;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import rest.logger.Config;

import org.objectweb.asm.Opcodes;

public class Utils implements Opcodes {
  public static void addBipushInsn(MethodVisitor mv, int val) {
    switch (val) {
      case 0:
        mv.visitInsn(ICONST_0);
        break;
      case 1:
        mv.visitInsn(ICONST_1);
        break;
      case 2:
        mv.visitInsn(ICONST_2);
        break;
      case 3:
        mv.visitInsn(ICONST_3);
        break;
      case 4:
        mv.visitInsn(ICONST_4);
        break;
      case 5:
        mv.visitInsn(ICONST_5);
        break;
      default:
        mv.visitLdcInsn(new Integer(val));
        break;
    }
  }

  
  public static void addThreadId(MethodVisitor mv){
	  mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;",false);
	  mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getId", "()J", false);
  }


	public static void addValueThisInsn(MethodVisitor mv, String desc, String methodNamePrefix) {
		Type t;
	
	    if (desc.startsWith("(")) {
	      t = Type.getReturnType(desc);
	    } else {
	      t = Type.getType(desc);
	    }
	    switch (t.getSort()) {
	      case Type.LONG:	    	 
	        mv.visitInsn(DUP);
	        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getId", "()J", false);
	        addThreadId(mv);
	        mv.visitMethodInsn(
	            INVOKESTATIC, Config.instance.analysisClass, methodNamePrefix + "ThreadId", "(JJ)V", false);
	        break;
	      case Type.OBJECT:	 
	        mv.visitInsn(DUP);
	        addThreadId(mv);
	        mv.visitMethodInsn(
	            INVOKESTATIC,
	            Config.instance.analysisClass,
	            methodNamePrefix + "MonitorObject",
	            "(Ljava/lang/Object;J)V", false);
	        break;
	      default:
	        System.err.println("Unknown field or method descriptor " + desc);
	        System.exit(1);
	    }
		
	}

}
