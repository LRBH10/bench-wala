package net.pradeo.klcfa;

public abstract class A1 {

  private A1 field;
  
  public void setField(A1 field) {
    this.field = field;
  }
  
  public A1 getField() {
    return field;
  }

  public A1(A1 a) {
    this.field = a;
  }

  public A1() {}

  public abstract void foo(A1 pa, int x);

  public void bar(A1 a, int x) {
//    System.out.println(this.getClass().getSimpleName() + " " + field.getClass().getSimpleName()
//        + "\n" + a.getClass().getSimpleName() + " " + a.field.getClass().getSimpleName() + "\n" + x
//        + "\n*************\n");

    a.foo(this.field, x - 1);
  }



}
