package com.benchcfa.kcfa;

public class B extends A {

	@Override
	public void foo(A a, int x) {

		if (x > 0) {
			A a1;
			A a2;
			if (x % 2 == 0) {
				a1 = a;
				a2 = this;
			} else {
				a1 = this;
				a2 =a;
			}

			a1.bar(a2, x);
		}

	}

}
