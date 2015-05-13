package com.moex.eif.benchmark;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import org.apache.commons.cli.ParseException;

public class Node {

  public static void main(String[] args) throws ParseException {
    HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(null);

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        hazelcastInstance.shutdown();
      }
    });
  }
}
