package rest.parseMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import org.objectweb.asm.Opcodes;

public class ForQueryClassVisitor extends ClassVisitor{
	String internalName;
	String superName;
	String classSignature;
	String[] interfaces;
	
	private HashMap<MethodSign, ArrayList<MetaData>> results;
	
	public ForQueryClassVisitor() {
		super(Opcodes.ASM5);
	}
	
	@Override 
	public void visit(int version, int access, 
			String name, String signature, String superName, String[] interfaces) {
		this.internalName = name;
		this.superName = superName;
		this.classSignature = signature;
		this.interfaces = interfaces;
		super.visit(version, access, name, signature, superName, interfaces);
	}
	
		@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature,
			String[] exceptions) {
		if(interfaces.length > 0 && interfaces[0].equals(("org/springframework/data/repository/Repository"))) {
			return new QueryMethodVisitor(name,signature);
		}
		return null;
	}

	
	class QueryMethodVisitor extends MethodVisitor{
		QueryAnnotationVisitor qav;	
		private String methodName;
		private String methodSignature;
		
		public QueryMethodVisitor(String name, String methodsign) {
			super(Opcodes.ASM5);
			this.methodName = name;
			this.methodSignature = methodsign;
		}
		
		@Override
		public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
			if("Lorg/springframework/data/jpa/repository/Query;".equals(desc)) {
				qav = new QueryAnnotationVisitor();	
				return qav; 
			}
			return null;
		}
		
		@Override
		public void visitEnd() {
			if(qav != null) {  // qav == null表示没有注解
				ArrayList<MetaData> metaDatas = qav.getParser().dealWithData();
				if(results == null) {
					results = new HashMap<MethodSign, ArrayList<MetaData>>();
				}
				results.put(new MethodSign(internalName,methodName), metaDatas);
			}else {
				MethodNameParser mnp = new MethodNameParser(methodName,classSignature);
				mnp.parse();
				if(results == null) {
					results = new HashMap<MethodSign, ArrayList<MetaData>>();
				}
				ArrayList<MetaData> metaDatas = new ArrayList<>();
				if(mnp.getRmd() != null) {
					metaDatas.add(mnp.getRmd());
				}
				if(mnp.getWmd() != null) {
					metaDatas.add(mnp.getWmd());
				}
				results.put(new MethodSign(internalName,methodName), metaDatas);
			}
		}
		
		class QueryAnnotationVisitor extends AnnotationVisitor{
			AbQueryParser qParser;
			
			public QueryAnnotationVisitor() {
				super(Opcodes.ASM5);
			}
			
			public AbQueryParser getParser() {
				return qParser;
			}
			@Override
			public void visit(String name, Object value) {
				if(name.equals("value")) {
					assert(value instanceof String);
					String query = (String) value;
					qParser = new JPQueryParser(query);
					qParser.parseQuery();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String testedProject = System.getProperty("test.classFiles", ".");
		LinkedList<File> files = findClassFiles(testedProject);
		
		InputStream in = null;
		ForQueryClassVisitor qv = new ForQueryClassVisitor();
		try {
			if(files != null && files.size() > 0) {
				for(File testfile:files) {
					in = new FileInputStream(testfile);
					ClassReader cr = new ClassReader(in);
					cr.accept(qv, 0);
					in.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		outputAsJson(qv);
		
		
		
	}
	
	public static void outputAsJson(ForQueryClassVisitor qv) {
		String outputJpaAsJsonFileName = System.getProperty("output.JsonFileName",System.getProperty("user.dir")+"/output.json");
		File output = new File(outputJpaAsJsonFileName);
		
		try {
			if(!output.exists()) {
				output.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(output);
			PrintWriter pw = new PrintWriter(fos);
			pw.println(qv.results.toString());
			pw.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
				
		}
	}
	
	public static LinkedList<File> findClassFiles(String path){
		 int fileNum = 0, folderNum = 0;
	     File file = new File(path);
	     LinkedList<File> results = new LinkedList<File>();
	     if (file.exists()) {
	          LinkedList<File> list = new LinkedList<File>();
	          File[] files = file.listFiles();
	          for (File file2 : files) {
		          if (file2.isDirectory()) {
		                 System.out.println("文件夹:" + file2.getAbsolutePath());
		                 list.add(file2);
		                 folderNum++;
		          } else {	                
		                 String fileName = file2.getName();
		                 String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
		                 if(fileType.equals("class")) {
			                 System.out.println("文件:" + file2.getAbsolutePath());
			                 results.add(file2);
			                 fileNum++;
		                 }
		          }
	          }
	          File temp_file;
	          while (!list.isEmpty()) {
	               temp_file = list.removeFirst();
	               files = temp_file.listFiles();
	               for (File file2 : files) {
	                    if (file2.isDirectory()) {
	                        System.out.println("文件夹:" + file2.getAbsolutePath());
	                        list.add(file2);
	                        folderNum++;
	                    } else {
	                    	 String fileName = file2.getName();
			                 String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			                 if(fileType.equals("class")) {
				                 System.out.println("文件:" + file2.getAbsolutePath());
				                 results.add(file2);
				                 fileNum++;
			                 }
	                    }
	                }
	            }
	        } else {
	            System.out.println("不存在目录!");
	        }
	        System.out.println("文件夹总个数:" + folderNum + ",class文件总个数:" + fileNum);
	        return results;
	}
}
