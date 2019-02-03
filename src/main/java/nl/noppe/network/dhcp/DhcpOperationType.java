package nl.noppe.network.dhcp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum DhcpOperationType {
    ADD("add"),
    OLD("old"),
    DELETED("del");

    private final String name;

    DhcpOperationType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static DhcpOperationType forValue(String value) {
        return Stream.of(values())
                .filter(dhcpOperationType -> dhcpOperationType.name.equals(value))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
