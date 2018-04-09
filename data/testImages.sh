#!/bin/bash

#Create directories by class

mkdir -p test
for i in `seq 0 9`;
do
    mkdir -p test/$i
done

while IFS='' read -r line || [[ -n "$line" ]]; do
    linearray=($line)
    from="${linearray[0]}"
    to="test/${linearray[1]}/"
    ln -sf $from $to
done < "test.txt"
