package com.moex.eif.benchmark.hazelcast;

import com.hazelcast.mapreduce.aggregation.Aggregations;
import com.hazelcast.mapreduce.aggregation.Supplier;
import com.moex.eif.benchmark.hazelcast.model.Data;

public class GetDistinctValues extends HazelcastBenchmark {

  @Override
  public Object run() throws Exception {
    return cache.aggregate(Supplier.all(Data::getStringVal), Aggregations.distinctValues());
  }
}
