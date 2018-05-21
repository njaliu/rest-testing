package rest.parseMethod;
public class MethodSign {
	private String className;
	private String methodName;
	
	public MethodSign(String internalName, String name) {
		this.className = internalName;
		this.methodName = name;
	}
	
	@Override
	public String toString() {
		return "\""+className+"#_#"+methodName+"\"";
	}
}
