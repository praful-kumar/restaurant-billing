package com.restaurant.restaurantbilling.mongoDbConfig;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "resturentCluster";
    }

    @Override
    public MongoClient mongoClient() {

        String connectionString = "mongodb+srv://praful1:C0cHUkpbIytAZpuy@resturentcluster.gqdlwfa.mongodb.net/?retryWrites=true&w=majority&appName=resturentCluster";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();


        return MongoClients.create(settings);
    }
}