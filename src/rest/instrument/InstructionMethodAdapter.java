package rest.instrument;

import java.io.IOException;

import org.json.JSONObject;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import rest.instrument.Utils;
import rest.logger.Config;



public class InstructionMethodAdapter extends MethodVisitor implements Opcodes{
	boolean isInit;
	String cname;
	private final GlobalStateForInstrumentation instrumentationState;
	 
	public InstructionMethodAdapter(MethodVisitor mv, String cname, boolean isInit, GlobalStateForInstrumentation instance) {
		super(ASM5, mv);
		this.isInit = isInit;
		this.instrumentationState = instance;
		this.cname = cname;
	}
	
	private static void addBipushInsn(MethodVisitor mv, int val) {
	    Utils.addBipushInsn(mv, val);
	}
	  
	private static void addThreadId(MethodVisitor mv){
		  Utils.addThreadId(mv);	 
	}
	
	private static void addSyn(MethodVisitor mv, String method) {
		if(GlobalStateForInstrumentation.instance.methodTosyn.containsKey(method)) {
			Utils.addBipushInsn(mv, 1);
		}else
			Utils.addBipushInsn(mv, 0);
	}
	
	private void addMonitorInsn(MethodVisitor mv, String insn, int opcode) {
		mv.visitInsn(DUP);
		addThreadId(mv);
		addBipushInsn(mv, instrumentationState.incAndGetId());
		addBipushInsn(mv, instrumentationState.getMid());
		mv.visitMethodInsn(INVOKESTATIC, Config.instance.analysisClass, insn, "(Ljava/lang/Object;JII)V", false);

		mv.visitInsn(opcode);
	}
	
	private void addValueThisInsn(MethodVisitor mv, String desc, String methodNamePrefix) {
	    Utils.addValueThisInsn(mv, desc, methodNamePrefix);
	 }
	
	private void addMethodCallInsn(MethodVisitor mv, int opcode, String owner, String name, String desc ) {
		addSyn(mv,owner+":"+name);
	    	addThreadId(mv);
		addBipushInsn(mv, instrumentationState.incAndGetId());
		addBipushInsn(mv, instrumentationState.getMid());
		mv.visitLdcInsn(owner);
		mv.visitLdcInsn(name);
		mv.visitLdcInsn(desc);
		if(opcode == INVOKESPECIAL)
			mv.visitMethodInsn(INVOKESTATIC, Config.instance.analysisClass, "INVOKESPECIAL", "(IJIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
		else if(opcode == INVOKEINTERFACE)
			mv.visitMethodInsn(INVOKESTATIC, Config.instance.analysisClass, "INVOKEINTERFACE", "(IJIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
		else if(opcode == INVOKEVIRTUAL)
			mv.visitMethodInsn(INVOKESTATIC, Config.instance.analysisClass, "INVOKEVIRTUAL", "(IJIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
		else if(opcode == INVOKESTATIC)
			mv.visitMethodInsn(INVOKESTATIC, Config.instance.analysisClass, "INVOKESTATIC", "(IJIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false);
		
	}
	
	@Override
	public void visitCode() {
	    instrumentationState.incMid();
	    mv.visitCode();
	}
	 
    @Override
	public void visitLineNumber(int lineNumber, Label label) {
	    mv.visitLineNumber(lineNumber, label);
	}
    
    @Override
    public void visitInsn(int opcode) {
      switch (opcode) {
        case MONITORENTER:
          addMonitorInsn(mv, "MONITORENTER", opcode);
          break;
        case MONITOREXIT:
          addMonitorInsn(mv, "MONITOREXIT", opcode);
          break;
        default:
        	  mv.visitInsn(opcode);
      }
    }
    
    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
	    	if(instrumentationState.isMethodJPA(owner, name)) {
	    		addMethodCallInsn(mv, opcode, owner, name, desc);
	    	}
	    	
	    	if(owner.equals("java/lang/Thread")){
	    		if(desc.equals("()V") && (name.equals("start") || name.equals("join"))) {
	    			addValueThisInsn(mv, "J", "GETTHIS_");
	    			addMethodCallInsn(mv, opcode, owner, name, desc);
	    		}
	    	}
	    	
	    	if(owner.equals("java/lang/Object")) {
	    		if(desc.equals("()V") && (name.equals("wait") || name.equals("notify") || name.equals("notifyAll"))) {
	    			addValueThisInsn(mv, "Ljava/lang/Object;", "GETTHIS_");
	    			addMethodCallInsn(mv, opcode, owner, name, desc);
	    		}
	    	}
	    	
	    	if(owner.matches("^java/util/concurrent/locks.*")) {
	    		if(desc.equals("()V") && (name.equals("lock") || name.equals("unlock") || name.equals("await") || name.equals("signal"))) {
	    			addValueThisInsn(mv, "Ljava/lang/Object;", "GETTHIS_");
	    			addMethodCallInsn(mv, opcode, owner, name, desc);
	    		}
	    	}
	    	
	    	mv.visitMethodInsn(opcode, owner, name, desc, itf);	
		
    }
  
    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
      mv.visitMaxs(
          maxStack + 8,
          maxLocals); //To change body of overridden methods use File | Settings | File Templates.
    }
  
}
