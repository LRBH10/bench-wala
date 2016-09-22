package net.pradeo.sourceAdder;

import java.util.ArrayList;
import java.util.List;

import net.pradeo.agesen.Animal;
import net.pradeo.agesen.Donkey;
import net.pradeo.agesen.Jaguar;



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
