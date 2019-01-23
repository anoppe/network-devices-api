package nl.noppe.network;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.log4j.Logger;

public class NewDeviceService {

    private static final Logger logger = Logger.getLogger(NewDeviceService.class);

    public NewDeviceResponse saveDevice(Device device) {

        logger.info("Request received: " + device);

        AmazonDynamoDB amazonDynamoDB = initDynamoClient();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        dynamoDBMapper.save(device);

        return new NewDeviceResponse(201);
    }

    private AmazonDynamoDB initDynamoClient() {
        String awsRegion = System.getenv().get("AWS_REGION");

        logger.debug("Current reqion: " + awsRegion);

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient();

        Regions regionsByName = Regions.fromName(awsRegion);
        Region region = Region.getRegion(regionsByName);

        dynamoDBClient.setRegion(region);

        return dynamoDBClient;
    }

}
