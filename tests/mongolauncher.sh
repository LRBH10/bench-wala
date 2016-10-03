#!/bin/bash

i=20001;
for f in *
do 
	echo "$i ${f:0:30} /suites/bench/$f android-bench"
	i=$[i+1];
done
