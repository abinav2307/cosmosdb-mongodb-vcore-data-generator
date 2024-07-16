package com.vcore.datagenerator;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.bson.Document;

public class SampleBsonDocumentGenerator {

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
     * Generates a sample BSON document in the specified size range to be inserted 
     * into the vCore cluster for benchmarking purposes.
     * 
     * @param documentSize The size of the sample document in KB to generate for the vCore cluster
     * @return
     */
    public Document generateSampleDocumentFromJsonObject(int documentSize) {
    	
    	String jsonStringForSampleDocument = createSampleJsonDocument(documentSize);		
    	Document sampleDocumentForIngestion = Document.parse(jsonStringForSampleDocument);
    	
    	return sampleDocumentForIngestion;
    }
    
    /**
     * Helper method to generate the sample document for the vCore cluster.
     * 
     * @param documentSizeInKB The size of the document in KB for the sample BSON document
     * @return
     */
    public String createSampleJsonDocument(int documentSizeInKB) {
        
    	JsonObject rootDocumentForBenchmarking = Json.createObjectBuilder().
        	add("name", "Abinav Rameesh").build();
    	
    	StringBuilder arrayOfSize1KB = new StringBuilder().
    		append("[").
    		append(role1ForDataGeneration).
    		append(",").
    		append(role2ForDataGeneration).
    		append(",").
    		append(role3ForDataGeneration).
    		append(",").
    		append(role4ForDataGeneration).
    		append(",").
    		append(role5ForDataGeneration)
    		.append("]");
    	
    	JsonArray rolesArray = Json.createReader(new StringReader(arrayOfSize1KB.toString())).readArray();
    	 
    	int sizeOfEachRolesArrayInKb = 1;
    	int numberOfRolesArraysToInclude = (int) (Math.ceil(documentSizeInKB)/sizeOfEachRolesArrayInKb);
    	for (int eachArrayToGenerate = 1; eachArrayToGenerate <= numberOfRolesArraysToInclude; eachArrayToGenerate++) {
    		
    		rootDocumentForBenchmarking = 
    			Json.createObjectBuilder(rootDocumentForBenchmarking).
    				add("roles".concat(String.valueOf(eachArrayToGenerate)), rolesArray).build();
    	}
    	
    	return rootDocumentForBenchmarking.toString();
    }
}
