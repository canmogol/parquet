package com.comodo.parquet.model;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * can | 8/23/17.
 */
public enum ProtocolEnum {
    TCP("TCP"), UDP("TCP"), ICMP("ICMP");

    private String name;

    ProtocolEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<ProtocolEnum> create(String name) {
        return Stream.of(ProtocolEnum.values())
            .filter(protocolEnum -> protocolEnum.getName().equals(name))
            .findFirst();
    }

}
