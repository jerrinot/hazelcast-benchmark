package com.moex.eif.benchmark.hazelcast.model;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;
import java.util.Objects;

public class Data implements IdentifiedDataSerializable {

  public static final int CLASS_ID = 1;
  public static final int FACTORY_ID = 99999;

  private String key;
  private String stringVal;
  private double doubleVal;
  private long longVal;
  private int intVal;

  public Data(){}

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
  public int getId() {
    return CLASS_ID;
  }

  public int getClassId() {
    return CLASS_ID;
  }

  public void writePortable(PortableWriter out) throws IOException {
    out.writeUTF("key", key);
    out.writeUTF("stringVal", stringVal);
    out.writeDouble("doubleVal", doubleVal);
    out.writeLong("longVal", longVal);
    out.writeInt("intVal", intVal);
  }


  @Override
  public void writeData(ObjectDataOutput out) throws IOException {
    out.writeUTF(key);
    out.writeUTF(stringVal);
    out.writeDouble(doubleVal);
    out.writeLong(longVal);
    out.writeInt(intVal);
  }

  public void readPortable(PortableReader in) throws IOException {
    key = in.readUTF("key");
    stringVal = in.readUTF("stringVal");
    doubleVal = in.readDouble("doubleVal");
    longVal = in.readLong("longVal");
    intVal = in.readInt("intVal");
  }


  @Override
  public void readData(ObjectDataInput in) throws IOException {
    key = in.readUTF();
    stringVal = in.readUTF();
    doubleVal = in.readDouble();
    longVal = in.readLong();
    intVal = in.readInt();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Data data = (Data) o;

    if (Double.compare(data.doubleVal, doubleVal) != 0) return false;
    if (longVal != data.longVal) return false;
    if (intVal != data.intVal) return false;
    if (key != null ? !key.equals(data.key) : data.key != null) return false;
    return !(stringVal != null ? !stringVal.equals(data.stringVal) : data.stringVal != null);

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = key != null ? key.hashCode() : 0;
    result = 31 * result + (stringVal != null ? stringVal.hashCode() : 0);
    temp = Double.doubleToLongBits(doubleVal);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (int) (longVal ^ (longVal >>> 32));
    result = 31 * result + intVal;
    return result;
  }

  //  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//
//    Data data = (Data) o;
//    return Objects.equals(key, data.key);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(key);
//  }

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
