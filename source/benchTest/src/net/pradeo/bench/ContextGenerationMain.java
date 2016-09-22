package net.pradeo.bench;

public class ContextGenerationMain {
	public static void main() {
		caller();
	}

	static void caller() {
		bar();
		bar();
		qux();
	}

	static void qux() {
		foo();

	}

	static void bar() {
		foo();
	}

	static void foo() {
		baz();
		baz();
	}

	static void baz() {
		corge();
	}

	static void corge() {
		System.out.println("Hello");
	}
}
