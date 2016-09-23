package com.benchcfa.bench;

import com.benchcfa.kcfa.A;
import com.benchcfa.kcfa.B;
import com.benchcfa.kcfa.C;

public class MainKCFA {
  
  public static void main() {
     A c = new C();
     A b = new B();
     c.bar(b, 5);
  }
  
   

}
