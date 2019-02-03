package nl.noppe.network.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import nl.noppe.network.model.Device;
import org.apache.log4j.Logger;

import java.util.List;

public class DeviceDao extends DynamoDbDao {

    private static final Logger logger = Logger.getLogger(DeviceDao.class);

    public DeviceDao() {
        super();
    }

    public void saveDevice(Device device) {
        logger.debug(String.format("Saving device: %s", device));
        dynamoDBMapper.save(device);
    }

    public List<Device> getAll() {
        logger.debug("Querying all devices");
        return dynamoDBMapper.scan(Device.class, new DynamoDBScanExpression());
    }

}
