package com.vcore.datagenerator;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.vcore.benchmark.DataIngestor;
import com.vcore.benchmark.Ingestion;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

public class Create {

	/**
	 * Role 1 - Sample first role for the sample BSON document to insert.
	 */
    private static final String role1ForDataGeneration =
    	new StringBuilder().
    		append("{\"companyName\": \"Microsoft Corporation\",\"department\": ").
    		append("\"Azure Data & AI\",\"teamName\": \"Azure Cosmos DB for MongoDB\",").
    		append("\"teamSubName\" : \"Azure Cosmos DB for MongoDB vCore\",").
    		append("\"isCurrentRole\" : \"Yes\",\"numYearsInRole\" : 1}").
    		toString();

    /**
	 * Role 2 - Sample first role for the sample BSON document to insert.
	 */
    private static final String role2ForDataGeneration = 
    	new StringBuilder().
    		append("{\"companyName\": \"Microsoft Corporation\",\"department\": ").
    		append("\"Azure Data & AI\",\"teamName\": \"Azure Cosmos DB\",").
    		append("\"teamSubName\" : \"High Availability, Cassandra & Customer Success\",").
    		append("\"isCurrentRole\" : \"No\",\"numYearsInRole\" : 2}").
    		toString();
    
    /**
	 * Role 3 - Sample first role for the sample BSON document to insert.
	 */
    private static final String role3ForDataGeneration = 
    	new StringBuilder().
    		append("{\"companyName\": \"PROS Inc.\",\"department\": ").
    		append("\"Cloud & Engineering\",\"teamName\": \"Stratus Revenue Optimizer\",").
    		append("\"teamSubName\" : \"Stratus Revenue Management\",").
    		append("\"isCurrentRole\" : \"No\",\"numYearsInRole\" : 2}").
    		toString();

    /**
	 * Role 4 - Sample first role for the sample BSON document to insert.
	 */
    private static final String role4ForDataGeneration = 
    	new StringBuilder().
    		append("{\"companyName\": \"PROS Inc.\", \"department\": ").
    		append("\"Science & Research\",\"teamName\": \"Revenue Optimization\",").
    		append("\"teamSubName\" : \"Hotel Revenue Optimization System\",").
    		append("\"isCurrentRole\" : \"No\",\"numYearsInRole\" : 2}").
    		toString();

    /**
	 * Role 5 - Sample first role for the sample BSON document to insert.
	 */
    private static final String role5ForDataGeneration = 
    	new StringBuilder().
    		append("{\"companyName\": \"PROS Inc.\",\"department\": ").
    		append("\"Science & Research\",\"teamName\": \"Revenue Optimization\",").
    		append("\"teamSubName\" : \"Revenue Optimization Simulator\",").
    		append("\"isCurrentRole\" : \"No\",\"numYearsInRole\" : 1}").
    		toString();
    
    /**
     * Connection String for the Cosmos DB for MongoDB vCore cluster
     */
    private static String MongovCoreConnectionString;
    
    /**
     * The database within which the documents will be inserted for the benchmarking run.
     */
    private static String MongovCoreDatabase;
    
    /**
     * The collection into which the documents will be inserted for the benchmarking run.
     */
    private static String MongovCoreCollection;
    
    /**
     * The average size of the BSON document for the benchmarking run.
     */
    private static int DocumentSizeInKB;
    
    /**
     * The degree of parallelism with which to insert documents for the benchmarking run.
     */
    private static int NumThreadsForIngestion;
    
    /**
     * The number of documents for each thread to insert.
     */
    private static int NumDocumentsPerThread;
        
    public static void main(String[] args) throws InterruptedException {
    	
    	((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);
    	
    	if (args.length > 0) {
    		MongovCoreConnectionString = args[0];
    	} if (args.length > 1) {
    		MongovCoreDatabase = args[1];
    	} if (args.length > 2) {
    		MongovCoreCollection = args[2];
    	} if (args.length > 3) {
    		DocumentSizeInKB = Integer.parseInt(args[3]);
    	} if (args.length > 4) {
    		NumThreadsForIngestion = Integer.parseInt(args[4]);
    	} if (args.length > 5) {
    		NumDocumentsPerThread = Integer.parseInt(args[5]);
    	}
    	
    	try (MongoClient mongoClient = MongoClients.create(MongovCoreConnectionString)) {
        	MongoDatabase sampleTrainingDB = mongoClient.getDatabase(MongovCoreDatabase);
        	MongoCollection<Document> employeeCollection = sampleTrainingDB.getCollection(MongovCoreCollection);
        	
        	SampleBsonDocumentGenerator documentGenerator = new SampleBsonDocumentGenerator();
        	ExecutorService es = Executors.newCachedThreadPool();
        	
        	for (int eachThread = 0; eachThread < NumThreadsForIngestion; eachThread++) {
        		DataIngestor dataIngestor = new DataIngestor(
        			documentGenerator, 
        			DocumentSizeInKB, 
        			employeeCollection, 
        			NumDocumentsPerThread);
        		es.execute(new Thread(dataIngestor));      		
        	}
        	
        	es.shutdown();
            while (!es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
            }
            
        	System.out.println("Completed ingestion!");
        }
    }
}
