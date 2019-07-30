package lifealien.test.intf;

import org.junit.Test;

public class Data extends DataClazz implements Parent{
	
	public Data() {
		System.out.println(name);
	}
	
	public Data(String name) {
		this();
		System.out.println(name);
	}
	
	@Test
	public void test1() {
//		name = "31123";
		System.out.println(name);
		data.getV();
//		System.out.println(Parent.a);
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String getV() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public String getV(String a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object getV() {
		// TODO Auto-generated method stub
		System.out.println("123");
		return null;
	}

}
