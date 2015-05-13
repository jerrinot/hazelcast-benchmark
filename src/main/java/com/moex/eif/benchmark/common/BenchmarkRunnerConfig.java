package com.moex.eif.benchmark.common;

public class BenchmarkRunnerConfig {

  private int totalObjects = 1_000_000;
  private int resultSetSize = 10_000;
  private int warmupIterations = 10;
  private int iterations = 100;

  public BenchmarkRunnerConfig() {
  }

  public int getTotalObjects() {
    return totalObjects;
  }

  public BenchmarkRunnerConfig setTotalObjects(int totalObjects) {
    this.totalObjects = totalObjects;
    return this;
  }

  public int getResultSetSize() {
    return resultSetSize;
  }

  public BenchmarkRunnerConfig setResultSetSize(int resultSetSize) {
    this.resultSetSize = resultSetSize;
    return this;
  }

  public int getWarmupIterations() {
    return warmupIterations;
  }

  public BenchmarkRunnerConfig setWarmupIterations(int warmupIterations) {
    this.warmupIterations = warmupIterations;
    return this;
  }

  public int getIterations() {
    return iterations;
  }

  public BenchmarkRunnerConfig  setIterations(int iterations) {
    this.iterations = iterations;
    return this;
  }
}
