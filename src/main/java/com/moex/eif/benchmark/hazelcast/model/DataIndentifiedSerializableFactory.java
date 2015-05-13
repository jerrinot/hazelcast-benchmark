package com.moex.eif.benchmark.hazelcast.model;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

/**
 * IMDG Guides example
 * Created on 13/05/15
 */
public class DataIndentifiedSerializableFactory implements DataSerializableFactory {

    public static final int FACTORY_ID = 99999;

    @Override
    public IdentifiedDataSerializable create(int typeId) {
        if (typeId == Data.CLASS_ID){
            return new Data();
        } else {
            return null;
        }
    }
}
