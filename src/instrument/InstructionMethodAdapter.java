package instrument;

import java.util.LinkedList;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class InstructionMethodAdapter extends MethodVisitor implements Opcodes{
	boolean isInit;
	boolean isSuperInitCalled;
	private final GlobalStateForInstrumentation instrumentationState;
	 
	public InstructionMethodAdapter(MethodVisitor mv, boolean isInit, GlobalStateForInstrumentation instance) {
		super(ASM5, mv);
		this.isInit = isInit;
		this.isSuperInitCalled = false;
		this.instrumentationState = instance;
	}
	
}
