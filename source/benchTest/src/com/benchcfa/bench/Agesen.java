package com.benchcfa.bench;

import com.benchcfa.agesen.Animal;
import com.benchcfa.agesen.Container;
import com.benchcfa.agesen.Donkey;
import com.benchcfa.agesen.Horse;
import com.benchcfa.agesen.Jaguar;
import com.benchcfa.agesen.Tiger;

public class Agesen {

  // kcfa
  public static void main() {
    kcfa();
    klcfa();
  }

  public static void kcfa() {
    Animal x1 = Animal.foo(new Horse(), new Donkey());
    Animal x2 = Animal.foo(new Tiger(), new Jaguar());

    x1.print();
  }

  public static void klcfa() {
    Container c1 = new Container(new Horse(), new Donkey());
    Container c2 = new Container(new Tiger(), new Jaguar());

    Animal x1 = Animal.fooklcfa(c1);
    Animal x2 = Animal.fooklcfa(c2);
    
    x2.print();
  }

}
