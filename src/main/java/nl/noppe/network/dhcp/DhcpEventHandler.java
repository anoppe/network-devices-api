package nl.noppe.network.dhcp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import nl.noppe.network.dao.DhcpEventDao;
import nl.noppe.network.device.NewDeviceService;
import nl.noppe.network.model.Device;
import nl.noppe.network.model.DhcpEvent;
import org.apache.log4j.Logger;

public class DhcpEventHandler implements RequestHandler<DhcpEvent, DhcpEventResponse> {

    private static final Logger logger = Logger.getLogger(DhcpEventHandler.class);

    private static NewDeviceService newDeviceService = new NewDeviceService();
    private static DhcpEventDao dhcpEventDao = new DhcpEventDao();

    @Override
    public DhcpEventResponse handleRequest(DhcpEvent dhcpEvent, Context context) {
        logger.info(String.format("Request received: %s", dhcpEvent));

        return handleDhcpEvent(dhcpEvent);
    }

    public DhcpEventResponse handleDhcpEvent(DhcpEvent event) {

        logger.info(String.format("DhcpEvent received: %s", event));

        try {
            dhcpEventDao.saveEvent(event);
        } catch (Exception e) {
            logger.error(String.format("An error occurred while saving a Dhcp Event: %s, with message: %s", event, e.getMessage()));
        }

        if ("add".equals(event.getOperation())) {
            logger.info("Event for operation 'add' received, saving device information");
            return addNewDevice(event);
        }

        return new DhcpEventResponse(200);
    }

    private DhcpEventResponse addNewDevice(DhcpEvent event) {
        Device device = new Device();
        device.setId(event.getMacAddress());
        device.setIpv4Address(event.getIpAddress());
        device.setName(event.getHostname());
        device.setRegistrationDate(event.getTimestamp());

        try {
            newDeviceService.saveDevice(device);
            return new DhcpEventResponse(200);
        } catch (Exception e) {
            logger.error(String.format("An error occurred while saving a new device with message: %s", e.getMessage()));
            return new DhcpEventResponse(500);
        }
    }

}
