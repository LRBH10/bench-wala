package com.benchcfa.bench;

import java.util.Random;

import com.benchcfa.bench.types.A;
import com.benchcfa.bench.types.C;
import com.benchcfa.bench.types.D;
import com.benchcfa.bench.types.X;
import com.benchcfa.bench.types.Y;
import com.benchcfa.bench.types.Z;
import com.benchcfa.bench.types.X;
import com.benchcfa.bench.types.Y;

public class DispatchTypeReceiverMain {
	public static void main() {
		long l = new Random().nextLong();
		A a = null;
		X x = null;
		if (l % 2 == 0) {
			a = new D();
			x = new Y();
		} else {
			a = new C();
			x = new Z();
		}
		a.bar(x);
	}
}
