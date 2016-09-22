package net.pradeo.bench;

import java.util.Random;

import net.pradeo.bench.types.A;
import net.pradeo.bench.types.C;
import net.pradeo.bench.types.D;
import net.pradeo.bench.types.X;
import net.pradeo.bench.types.Y;
import net.pradeo.bench.types.Z;
import net.pradeo.bench.types.X;
import net.pradeo.bench.types.Y;

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
