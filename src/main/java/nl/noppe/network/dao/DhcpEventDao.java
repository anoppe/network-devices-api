package nl.noppe.network.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import nl.noppe.network.model.DhcpEvent;
import org.apache.log4j.Logger;

import java.util.List;

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

}
