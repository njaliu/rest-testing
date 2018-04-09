package instrument;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class InstructionClassAdapter extends ClassVisitor{
	
	public InstructionClassAdapter(ClassVisitor cv) {
	    super(Opcodes.ASM5, cv);
	  }
}
