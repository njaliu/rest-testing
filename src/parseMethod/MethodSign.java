package parseMethod;
public class MethodSign {
	private String className;
	private String methodName;
	private String signature;
	
	public MethodSign(String internalName, String name, String signature) {
		this.className = internalName;
		this.methodName = name;
		this.signature = signature;
	}
	
	@Override
	public String toString() {
		return "{"+className+","+methodName+","+signature+"}";
	}
}
