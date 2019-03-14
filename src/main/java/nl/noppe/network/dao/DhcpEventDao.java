package nl.noppe.network.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import nl.noppe.network.model.DhcpEvent;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DhcpEventDao extends DynamoDbDao {

    private static final Logger logger = Logger.getLogger(DhcpEventDao.class);

    public DhcpEventDao() {
        super();
    }

    public void saveEvent(DhcpEvent dhcpEvent) {
        logger.debug(String.format("Saving dhcpEvent: %s", dhcpEvent));
        dynamoDBMapper.save(dhcpEvent);
    }

    public List<DhcpEvent> getAll() {
        logger.debug("Querying all dhcp events");
        return dynamoDBMapper.scan(DhcpEvent.class, new DynamoDBScanExpression());
    }

    public List<DhcpEvent> getEventsForDevice(String deviceId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":deviceId", new AttributeValue().withS(deviceId));

        DynamoDBQueryExpression<DhcpEvent> queryExpression = new DynamoDBQueryExpression<DhcpEvent>()
                .withKeyConditionExpression("macAddress = :deviceId")
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.query(DhcpEvent.class, queryExpression);
    }

}
