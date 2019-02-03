package nl.noppe.network.device;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import nl.noppe.network.dao.DeviceDao;
import nl.noppe.network.model.Device;
import org.apache.log4j.Logger;

public class NewDeviceService {

    private static final Logger logger = Logger.getLogger(NewDeviceService.class);

    private static final DeviceDao DEVICE_DAO = new DeviceDao();

    public NewDeviceResponse saveDevice(Device device) {
        logger.info("Request received: " + device);

        try {
            DEVICE_DAO.saveDevice(device);
        } catch (DynamoDBMappingException e) {
            logger.error(String.format("Error occurred saving device: %s", device), e);
            return new NewDeviceResponse(500);
        }


        return new NewDeviceResponse(201);
    }

}
