package com.moex.eif.benchmark.hazelcast.model;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;

public class Data implements Portable {

  public static final int CLASS_ID = 1;
  public static final int FACTORY_ID = 99999;

  private String key;
  private String stringVal;
  private double doubleVal;
  private long longVal;
  private int intVal;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getStringVal() {
    return stringVal;
  }

  public void setStringVal(String stringVal) {
    this.stringVal = stringVal;
  }

  public double getDoubleVal() {
    return doubleVal;
  }

  public void setDoubleVal(double doubleVal) {
    this.doubleVal = doubleVal;
  }

  public long getLongVal() {
    return longVal;
  }

  public void setLongVal(long longVal) {
    this.longVal = longVal;
  }

  public int getIntVal() {
    return intVal;
  }

  public void setIntVal(int intVal) {
    this.intVal = intVal;
  }

  @Override
  public int getFactoryId() {
    return FACTORY_ID;
  }

  @Override
  public int getClassId() {
    return CLASS_ID;
  }

  @Override
  public void writePortable(PortableWriter out) throws IOException {
    out.writeUTF("key", key);
    out.writeUTF("stringVal", stringVal);
    out.writeDouble("doubleVal", doubleVal);
    out.writeLong("longVal", longVal);
    out.writeInt("intVal", intVal);
  }

  @Override
  public void readPortable(PortableReader in) throws IOException {
    key = in.readUTF("key");
    stringVal = in.readUTF("stringVal");
    doubleVal = in.readDouble("doubleVal");
    longVal = in.readLong("longVal");
    intVal = in.readInt("intVal");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Data data = (Data) o;

    return !(key != null ? !key.equals(data.key) : data.key != null);

  }

  @Override
  public int hashCode() {
    return key != null ? key.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "HzData {" +
           "intVal=" + intVal +
           ", longVal=" + longVal +
           ", doubleVal=" + doubleVal +
           ", stringVal='" + stringVal + '\'' +
           ", key='" + key + '\'' +
           '}';
  }
}
