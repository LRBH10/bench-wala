package net.pradeo.kcfa;

public class C extends A {

	@Override
	public void foo(A a, int x) {
		
		if (x > 0) {

			if (x % 2 == 0)
				a.bar(this, x);
			else
				this.bar(a, x);
		}

	}

}
