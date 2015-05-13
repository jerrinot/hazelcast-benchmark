#!/bin/bash
SCRIPT=$(readlink -f "$0")
BINDIR=$(dirname "$SCRIPT")
HOME=$(dirname "$BINDIR")

cd $HOME
CLASS_PATH=$HOME/config:$HOME/lib/*

MEM_OPTS="-Xms8g -Xmx8g -XX:+HeapDumpOnOutOfMemoryError"
GC_OPTS="-XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -Xloggc:logs/hazelcast-gc.log"
JAVA_OPTS="-server -showversion $MEM_OPTS $GC_OPTS"

java $JAVA_OPTS -cp $CLASS_PATH com.moex.eif.benchmark.Node
