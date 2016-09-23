package com.benchcfa.bench;

import com.benchcfa.sourceAdder.SourceAdder;

public class MainSourceAdder {
  public static void main() {
    SourceAdder src = new SourceAdder();

    src.initAnimals();
    src.initAnimals();

    src.printAnimals();
  }
}
