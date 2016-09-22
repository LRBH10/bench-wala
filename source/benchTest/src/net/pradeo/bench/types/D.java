package net.pradeo.bench.types;

public class D extends A {
	@Override
	public void doo() {
	}
	
	@Override
	public void bar(X x) {
		super.doo();
		x.make();
	}
}
