package com.benchcfa.bench.types;

public class C extends B {
	@Override
	public void doo() {
	}

	@Override
	public void bar(X x) {
		System.out.println("C");
		this.doo();
		x.make();
	}
}
