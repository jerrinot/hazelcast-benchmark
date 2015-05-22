package com.moex.eif.benchmark;

import com.moex.eif.benchmark.common.BenchmarkRunner;
import com.moex.eif.benchmark.common.BenchmarkRunnerConfig;
import com.moex.eif.benchmark.hazelcast.GetDistinctValues;
import com.moex.eif.benchmark.hazelcast.GetByPredicate;


public class Main {

  public static void main(String[] args) throws Exception {
    BenchmarkRunnerConfig config = new BenchmarkRunnerConfig()
        .setTotalObjects(1_000_000)
        .setResultSetSize(10_000)
        .setWarmupIterations(10)
        .setIterations(100);

    new BenchmarkRunner()
        .addBenchmark(new GetByPredicate())
//        .addBenchmark(new GetDistinctValues())
        .run(config);
  }
}
