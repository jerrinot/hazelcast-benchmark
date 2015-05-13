package com.moex.eif.benchmark.hazelcast;

import com.hazelcast.query.PredicateBuilder;

public class GetByPredicate extends HazelcastBenchmark {
  @Override
  public Object run() throws Exception {
    PredicateBuilder predicate = new PredicateBuilder()
        .getEntryObject().get("stringVal")
        .equal(dataGenerator.getAnyUniqueString());

    return cache.entrySet(predicate);
  }
}
