package com.benchcfa.sourceAdder;

import java.util.ArrayList;
import java.util.List;

import com.benchcfa.agesen.Animal;
import com.benchcfa.agesen.Donkey;
import com.benchcfa.agesen.Jaguar;



public class SourceAdder {
  List<Animal> animals = new ArrayList<Animal>();

  public void initAnimals() {
    animals.add(new Donkey());
    animals.add(new Jaguar());
  }

  public void printAnimals() {
    for (Animal a : animals) {
      a.print();
    }
  }

}
