package net.pradeo.bench;

import net.pradeo.bench.alias.Pair;
import net.pradeo.bench.types.A;
import net.pradeo.bench.types.C;
import net.pradeo.bench.types.D;

public class AliasMain {
	public static void main() {
		Pair p1 =new Pair("key1", new C());
		Pair p2 =new Pair("key2", new D());
		Pair p3 = p2;
		A a =(A) p1.second;
		a.doo();
		
		A a2 =(A) p3.second;
		a2.doo();
	}
}
