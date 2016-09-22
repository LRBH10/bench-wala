package net.pradeo.klcfa;



public class B1 extends A1 {
  
  public B1() {
  }
  
  public B1(A1 a) {
   super(a);
  }
  
   
  
  @Override
  public void foo(A1 a, int x) {

      if (x > 0) {
          A1 a1;
          A1 a2;
          if (x % 2 == 0) {
              a1 = a;
              a2 = this.getField();
          } else {
              a1 = this.getField();
              a2 =a;
          }
          a1.bar(a2, x);
      }

  }
  
 

}
