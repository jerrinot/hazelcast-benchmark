package com.moex.eif.benchmark.hazelcast;

import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;
import com.moex.eif.benchmark.hazelcast.model.Data;

import java.util.Map;
import java.util.Set;

public class GetByPredicate extends HazelcastBenchmark {
  @Override
  public Object run() throws Exception {
    PredicateBuilder predicate = new PredicateBuilder()
        .getEntryObject().get("stringVal")
        .equal(dataGenerator.getAnyUniqueString());

      long startTime = System.currentTimeMillis();
      Set<Map.Entry<String, Data>> entries = cache.entrySet(predicate);
      long endTime = System.currentTimeMillis();
      long elapsedTime = endTime-startTime;
      System.out.println("Elapsed " + elapsedTime);
      return entries;
  }
}
