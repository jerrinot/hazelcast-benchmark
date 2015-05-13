package com.moex.eif.benchmark.common;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class BenchmarkRunner {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final MetricRegistry metrics = new MetricRegistry();
  private final List<Benchmark> benchmarks = new LinkedList<>();

  public BenchmarkRunner() {
  }

  public BenchmarkRunner addBenchmark(Benchmark benchmark) {
    benchmarks.add(benchmark);
    return this;
  }

  public void run() {
    run(new BenchmarkRunnerConfig());
  }

  public void run(BenchmarkRunnerConfig config) {
    if (config == null) {
      throw new IllegalArgumentException("config must not be null");
    }

    if (benchmarks.isEmpty()) {
      throw new IllegalStateException("Please add at least one benchmark");
    }

    Map<String, BenchmarkResult> results = new LinkedHashMap<>();
    for (Benchmark benchmark : benchmarks) {
      String benchmarkName = benchmark.getName();

      logger.info("--------------------------------------------------");
      logger.info("# Benchmark: {}", benchmarkName);
      logger.info("--------------------------------------------------");
      logger.info("# Total Objects: {}", config.getTotalObjects());
      logger.info("# Warmup: {}", config.getWarmupIterations());
      logger.info("# Iterations: {}", config.getIterations());
      logger.info("--------------------------------------------------");

      try {
        logger.info("# benchmark.setUp...");
        benchmark.setUp(config);

        logger.info("# benchmark.warmup...");
        for (int i = 0; i < config.getWarmupIterations(); i++) {
          benchmark.run();
        }

        logger.info("# benchmark.run...");
        final Timer timer = metrics.timer(benchmarkName);
        for (int i = 0; i < config.getIterations(); i++) {
          try (Timer.Context context = timer.time()) {
            benchmark.run();
          }
        }

        BenchmarkResult result = new BenchmarkResult(timer);
        results.put(benchmarkName, result);

        logger.info("# Result:");
        logger.info(format("   iterations = %d", result.getCount()));
        logger.info(format("         rate = %2.2f ops/s", convertRate(result.getMeanRate())));
        logger.info(format("          min = %2.2f ms", convertDuration(result.getMin())));
        logger.info(format("         mean = %2.2f ms", convertDuration(result.getMean())));
        logger.info(format("          max = %2.2f ms", convertDuration(result.getMax())));
        logger.info(format("       stdDev = %2.2f ms", convertDuration(result.getStdDev())));
        logger.info(format("       median = %2.2f ms", convertDuration(result.getMedian())));
        logger.info(format("         95%% <= %2.2f ms", convertDuration(result.getPercentile95th())));
        logger.info(format("         99%% <= %2.2f ms", convertDuration(result.getPercentile99th())));
      } catch (Exception e) {
        results.put(benchmarkName, null);

        logger.error("# Failed: ", e);
      } finally {
        logger.info("# benchmark.tearDown...");
        try {
          benchmark.tearDown();
        } catch (Exception e) {
          logger.warn("benchmark.tearDown failed: ", e);
        }
      }
    }

    pinrtStatistic(results);
  }

  private void pinrtStatistic(Map<String, BenchmarkResult> results) {
    String rowTemplate = "%-50s\t%-10s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s";

    logger.info("--------------------------------------------------");
    logger.info(String.format(rowTemplate,
                              "Benchmark",
                              "Iterations",
                              "rate,ops/s",
                              "min,ms",
                              "mean,ms",
                              "max,ms",
                              "stdDev,ms"));
    for(String benchmarkName : results.keySet()) {
      BenchmarkResult result = results.get(benchmarkName);

      logger.info(String.format(rowTemplate,
                                benchmarkName,
                                result != null ? format("%d", result.getCount()) : "FAILED",
                                result != null ? String.format("%2.2f", convertRate(result.getMeanRate())) : "FAILED",
                                result != null ? String.format("%2.2f", convertDuration(result.getMin())) : "FAILED",
                                result != null ? String.format("%2.2f", convertDuration(result.getMean())) : "FAILED",
                                result != null ? String.format("%2.2f", convertDuration(result.getMax())) : "FAILED",
                                result != null ? String.format("%2.2f",  convertDuration(result.getStdDev())) : "FAILED"));
    }
  }

  private double convertRate(double rate) {
    return rate * TimeUnit.SECONDS.toSeconds(1L);
  }

  private double convertDuration(double duration) {
    return duration * (1.0D / (double) TimeUnit.MILLISECONDS.toNanos(1L));
  }
}
