package com.moex.eif.benchmark.common;

import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;

/**
 * @author <a href="mailto:dmitry.lisin@moex.com">Dmitry Lisin</a>
 */
public class BenchmarkResult {
  private final long count;
  private final double meanRate;
  private final double min;
  private final double mean;
  private final double max;
  private final double stdDev;
  private final double median;
  private final double percentile95th;
  private final double percentile99th;

  public BenchmarkResult(Timer timer) {
    this.count = timer.getCount();
    this.meanRate = timer.getMeanRate();

    Snapshot snapshot = timer.getSnapshot();
    this.min = (double) snapshot.getMin();
    this.mean = snapshot.getMean();
    this.max = (double) snapshot.getMax();
    this.stdDev = snapshot.getStdDev();
    this.median = snapshot.getMedian();
    this.percentile95th = snapshot.get95thPercentile();
    this.percentile99th = snapshot.get99thPercentile();
  }

  public long getCount() {
    return count;
  }

  public double getMeanRate() {
    return meanRate;
  }

  public double getMin() {
    return min;
  }

  public double getMean() {
    return mean;
  }

  public double getMax() {
    return max;
  }

  public double getStdDev() {
    return stdDev;
  }

  public double getMedian() {
    return median;
  }

  public double getPercentile95th() {
    return percentile95th;
  }

  public double getPercentile99th() {
    return percentile99th;
  }
}
