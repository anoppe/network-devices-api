package nl.noppe.network.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "dhcp-events-v2")
public class DhcpEvent {

    private String operation;
    private String macAddress;
    private String ipAddress;
    private String hostname;
    private String timestamp;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @DynamoDBHashKey(attributeName = "macAddress")
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @DynamoDBRangeKey(attributeName = "timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DhcpEvent{" +
                "operation=" + operation +
                ", macAddress='" + macAddress + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", hostname='" + hostname + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

}
