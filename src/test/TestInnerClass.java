package test;
import test.TestInnerClass.InnerClass;

public class TestInnerClass {
	static InnerClass pin;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestInnerClass ic = new TestInnerClass();
		InnerClass in = ic.getInnerClass();
		System.out.println("hello world!");
	}
	
	public InnerClass getInnerClass() {
		InnerClass ic = new InnerClass();
		return ic;
	}
	
	private String outerName;
    private int outerAge;
    
    public class InnerClass{
        private String innerName;
        private int innerAge;
    }
	
	
}
