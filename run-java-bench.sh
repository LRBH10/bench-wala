#!/bin/bash

java -jar walaTest-run.jar $1 1 >"$1.1.out"
java -jar walaTest-run.jar $1 2 >"$1.2.out"
java -jar walaTest-run.jar $1 3 >"$1.3.out"
java -jar walaTest-run.jar $1 4 >"$1.4.out"
java -jar walaTest-run.jar $1 5 >"$1.5.out"
java -jar walaTest-run.jar $1 6 >"$1.6.out"

