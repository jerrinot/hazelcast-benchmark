package com.moex.eif.benchmark.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PartitionedMap<K, V> {
  private final List<Map<K, V>> partitions;

  private final AtomicInteger currentPartition = new AtomicInteger();

  public PartitionedMap(Map<K, V> map, int size) {
    int expectSize = (map.size() + size - 1) / size;
    int parsedSize = 0;

    int mapSize = map.size();
    int counter = 0;
    int listIndex = 0;

    partitions = new ArrayList<>(expectSize);
    for (int i = 0; i < expectSize; i++) {
      partitions.add(new HashMap<>());
    }

    for (Map.Entry<K, V> entry : map.entrySet()) {
      parsedSize++;
      counter++;

      partitions.get(listIndex).put(entry.getKey(), entry.getValue());

      if (parsedSize == size || counter == mapSize) {
        parsedSize = 0;
        listIndex++;
      }
    }
  }

  public Map<K, V> next() {
    return partitions.get(currentPartition.getAndIncrement());
  }

  public int size() {
    return partitions.size();
  }
}
