package net.pradeo.klcfa;


public class C1 extends A1 {
  public C1() {

  }

  public C1(A1 a) {
    super(a);
  }

  @Override
  public void foo(A1 a, int x) {

    if (x > 0) {

      if (x % 2 == 0)
        a.bar(this.getField(), x);
      else
        this.getField().bar(a, x);
    }

  }
}
