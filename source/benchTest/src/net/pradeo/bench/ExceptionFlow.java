package net.pradeo.bench;


public class ExceptionFlow{
	public static class E1 extends Exception {}
        public static class E2 extends E1 {}

	public static void foo() throws E1 {
		E2 e2 = new E2();
		throw e2;
	}
	public static void bar() throws E2 {}


	public static void main( ){
		try {
			foo();
			bar();
		} catch (E2 e2) {
			e2.printStackTrace();
		} catch (E1 e1) {
			e1.printStackTrace();
		} 
	}
}

