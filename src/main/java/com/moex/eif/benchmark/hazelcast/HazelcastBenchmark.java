package com.moex.eif.benchmark.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.moex.eif.benchmark.common.Benchmark;
import com.moex.eif.benchmark.common.BenchmarkRunnerConfig;
import com.moex.eif.benchmark.hazelcast.model.Data;
import com.moex.eif.benchmark.common.util.PartitionedMap;

public abstract class HazelcastBenchmark extends Benchmark {

  private HazelcastInstance hz;
  protected IMap<String, Data> cache;

  @Override
  public void setUp(BenchmarkRunnerConfig config) throws Exception {
    super.setUp(config);

    ClientConfig clientConfig = new XmlClientConfigBuilder("hazelcast-client.xml").build();
    hz = HazelcastClient.newHazelcastClient(clientConfig);
    cache = hz.getMap(DISTRIBUTED_WITH_BACKUP_SYNC);

    PartitionedMap<String, Data> partitionedMap =
        new PartitionedMap<>(dataGenerator.getSampleMap(), 100_000);
    for (int i = 0; i < partitionedMap.size(); i++) {
      cache.putAll(partitionedMap.next());
    }
  }

  @Override
  public void tearDown() {
    cache.destroy();
    hz.shutdown();
  }
}
