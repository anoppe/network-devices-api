package nl.noppe.network.device;

import nl.noppe.network.dao.DeviceDao;
import nl.noppe.network.model.Device;
import org.apache.log4j.Logger;

import java.util.List;

public class ListDeviceService {

    private static final Logger logger = Logger.getLogger(ListDeviceService.class);

    private static final DeviceDao DEVICE_DAO = new DeviceDao();

    public List<Device> getDevices() {
        logger.info("Request for all devices received");
        return DEVICE_DAO.getAll();
    }

    public Device getDeviceByMac(String macAddress) {
        logger.info(String.format("Request for device with macAddress received: %s", macAddress));
        return DEVICE_DAO.getByMac(macAddress);
    }
}
