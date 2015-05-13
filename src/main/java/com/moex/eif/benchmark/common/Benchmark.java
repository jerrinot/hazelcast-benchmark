package com.moex.eif.benchmark.common;

import com.moex.eif.benchmark.hazelcast.model.DataGenerator;

import org.apache.commons.lang3.StringUtils;

public abstract  class Benchmark {
  public static final String DISTRIBUTED_WITH_BACKUP_SYNC = "distributedWithBackup";

  private final String name;

  protected DataGenerator dataGenerator;

  public Benchmark() {
    this(null);
  }

  public Benchmark(String name) {
    if (StringUtils.isEmpty(name)) {
      name = getClass().getSimpleName();
    }

    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setUp(BenchmarkRunnerConfig config) throws Exception {
    dataGenerator = new DataGenerator(config.getTotalObjects(), config.getResultSetSize());
  }

  public void tearDown() throws Exception {
  }

  public abstract Object run() throws Exception;
}
