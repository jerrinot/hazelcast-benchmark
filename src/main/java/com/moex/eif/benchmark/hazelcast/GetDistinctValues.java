package com.moex.eif.benchmark.hazelcast;

import com.hazelcast.mapreduce.aggregation.Aggregations;
import com.hazelcast.mapreduce.aggregation.Supplier;
import com.moex.eif.benchmark.hazelcast.model.Data;

import java.util.Set;

public class GetDistinctValues extends HazelcastBenchmark {

  @Override
  public Object run() throws Exception {
    long startTime = System.currentTimeMillis();
    Set<Object> aggregate = cache.aggregate(Supplier.all(Data::getStringVal), Aggregations.distinctValues());
    long endTime = System.currentTimeMillis();
    long elapsedTime = endTime-startTime;
    System.out.println("Elapsed " + elapsedTime);
    return aggregate;
  }
}
