package net.pradeo.bench;

import net.pradeo.klcfa.A1;
import net.pradeo.klcfa.B1;
import net.pradeo.klcfa.C1;


public class MainKLCFA {
  public static void main() {
    A1 c = new C1();
    A1 b = new B1();
    c.setField(b);
    b.setField(c);
    c.bar(b, 5);
  }
}
