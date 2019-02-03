package nl.noppe.network.dao;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.log4j.Logger;

public abstract class DynamoDbDao {

    private static final Logger logger = Logger.getLogger(DynamoDbDao.class);
    final DynamoDBMapper dynamoDBMapper;

    public DynamoDbDao() {
        AmazonDynamoDB amazonDynamoDB = initDynamoClient();
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
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
