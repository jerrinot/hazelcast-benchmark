package com.moex.eif.benchmark.hazelcast.model;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;

class DataPortableFactory implements PortableFactory {

  @Override
  public Portable create(int classId) {
//    if (Data.CLASS_ID == classId) {
//      return new Data();
//    }
//
    return null;
  }
}
