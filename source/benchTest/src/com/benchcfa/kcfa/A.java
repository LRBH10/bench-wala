package com.benchcfa.kcfa;

public abstract class A {

	public abstract  void foo(A pa, int x);

	public void bar(A a, int x) {
//		System.out
//				.println(this.getClass().getSimpleName() + "\n"
//						+ a.getClass().getSimpleName() + "\n" + x
//						+ "\n*************\n");
		a.foo(this, x - 1);
	}

}
