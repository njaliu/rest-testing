package parseMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import jdk.internal.org.objectweb.asm.Opcodes;

public class ForQueryVisitor extends ClassVisitor{
	String internalName;
	String superName;
	String classSignature;
	String[] interfaces;
	
	private HashMap<MethodSign, ArrayList<MetaData>> results;
	
	public ForQueryVisitor() {
		super(Opcodes.ASM5);
		results = new HashMap<>();
	}
	
	@Override 
	public void visit(int version, int access, 
			String name, String signature, String superName, String[] interfaces) {
		internalName = name;
		this.superName = superName;
		this.classSignature = signature;
		this.interfaces = interfaces;
		super.visit(version, access, name, signature, superName, interfaces);
	}
	
		@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature,
			String[] exceptions) {
		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
		return new QueryMethodVisitor(mv,results,internalName,classSignature,name,signature);
	}
	
	@Override
	public void visitEnd() {
		if(results != null) {
			System.out.println(results.toString());
		}
	}
	
	class QueryMethodVisitor extends MethodVisitor{
		QueryAnnotationVisitor qav;
		private HashMap<MethodSign,ArrayList<MetaData>> result;
		private String internalName;
		private String methodName;
		private String sign;
		private String classSignature;
		public QueryMethodVisitor(MethodVisitor mv,HashMap<MethodSign,ArrayList<MetaData>> result,
								String internalName, String signature, String name, String sign) {
			super(Opcodes.ASM5,mv);
			this.result = result;
			this.internalName = internalName;
			this.methodName = name;
			this.sign = sign;
			this.classSignature = signature;
		}
		
		@Override
		public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		//	System.out.println(desc);
			AnnotationVisitor av = super.visitAnnotation(desc, visible);
			if("Lorg/springframework/data/jpa/repository/Query;".equals(desc)) {
				qav = new QueryAnnotationVisitor(av);	
				return qav; 
			}
			return av;
		}
		
		@Override
		public void visitEnd() {
			if(qav != null) {
				ArrayList<MetaData> metaDatas = qav.getParser().dealWithData();
				assert(results != null);  //results不能为空
				result.put(new MethodSign(internalName,methodName,sign), metaDatas);
			}else {
				MethodNameParser mnp = new MethodNameParser(methodName,classSignature);
				mnp.parse();
				assert(results != null);
				ArrayList<MetaData> metaDatas = new ArrayList<>();
				if(mnp.getRmd() != null) {
					metaDatas.add(mnp.getRmd());
				}
				if(mnp.getWmd() != null) {
					metaDatas.add(mnp.getWmd());
				}
				result.put(new MethodSign(internalName,methodName,sign), metaDatas);
			}
		}
		
		class QueryAnnotationVisitor extends AnnotationVisitor{
/** Boolean nativeSql = false;. */
			AbQueryParser qParser;
			
			public QueryAnnotationVisitor(AnnotationVisitor av) {
				super(Opcodes.ASM5,av);
			}
			
			public AbQueryParser getParser() {
				return qParser;
			}
			@Override
			public void visit(String name, Object value) {
				//目前只处理了JPQL，native sql需要之后迭代
//				System.out.println("name: " + name + " value: " + value);
				
//				if(name.equals("native") && value.toString().equals("true")) {
//					nativeSql = true;
//				}
				if("value".equals(name)) {
					assert value instanceof String;
					String query = (String) value;
//					if(nativeSql)
//						qParser = new NativeQueryParser(query);
//					else 
					qParser = new JPQueryParser(query);
					qParser.parseQuery();
				}
				super.visit(name, value);
			}
		}
				}
	
	public static void main(String[] args) {
		File testfile = new File(System.getProperty("user.dir")+"/PetRepository.class");
		InputStream in = null;
		ForQueryVisitor qv = new ForQueryVisitor();
		try {
			in = new FileInputStream(testfile);
			ClassReader cr = new ClassReader(in);
			cr.accept(qv, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File output = new File(System.getProperty("user.dir")+"/output.txt");
		if(!output.exists()) {
			try {
				output.createNewFile();
				FileOutputStream fos = new FileOutputStream(output);
				PrintWriter pw = new PrintWriter(fos);
				pw.write(qv.results.toString());
				pw.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
	}
}
