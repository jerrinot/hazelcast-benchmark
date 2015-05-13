package com.moex.eif.benchmark.hazelcast.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;

public class DataGenerator {

  protected final int overall;
  protected final int recordsPerUnique;
  protected AtomicInteger count;

  protected final List<String> uniqueStringValues;
  private final List<Data> sampleList;
  private final Map<String, Data> sampleMap;
  private final Integer someInt;

  public DataGenerator(int overall, int recordsPerUnique) {
    this.overall = overall;
    this.recordsPerUnique = recordsPerUnique;

    int uniquesCount = overall / recordsPerUnique;
    if (uniquesCount == 0) {
      uniquesCount = 1;
    }
    uniqueStringValues = new ArrayList<>(uniquesCount);
    for (int i = 0; i < uniquesCount; i++) {
      uniqueStringValues.add(i, randomAlphanumeric(30));
    }

    sampleMap = new HashMap<>(overall);
    sampleList = new ArrayList<>(overall);
    count = new AtomicInteger();
    for (int i = 0; i < overall; i++) {
      Data value = generate();
      sampleMap.put(value.getKey(), value);
      sampleList.add(i, value);
    }

    someInt = sampleList.get(0).getIntVal();

    //reset counter
    count = new AtomicInteger();
  }

  protected Data generate() {
    int index = count.getAndIncrement() / recordsPerUnique;
    String indexedField = getUniqueStringValues().get(index);

    Data data = new Data();
    data.setKey(randomAlphanumeric(7));
    data.setStringVal(indexedField);
    data.setIntVal(nextInt(0, Integer.MAX_VALUE));
    data.setLongVal(nextLong(0, Long.MAX_VALUE));
    data.setDoubleVal(nextDouble(0.0, Double.MAX_VALUE));

    return data;
  }

  public Integer getSomeInt() {
    return someInt;
  }

  public String nextKey() {
    return sampleList.get(count.getAndIncrement()).getKey();
  }

  public Data nextValue() {
    return sampleList.get(count.getAndIncrement());
  }

  public String getAnyUniqueString() {
    return uniqueStringValues.get(0);
  }

  public List<String> getUniqueStringValues() {
    return Collections.unmodifiableList(uniqueStringValues);
  }

  public List<Data> getSampleList() {
    return Collections.unmodifiableList(sampleList);
  }

  public Map<String, Data> getSampleMap() {
    return Collections.unmodifiableMap(sampleMap);
  }
}
