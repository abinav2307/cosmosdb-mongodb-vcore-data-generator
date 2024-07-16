package com.vcore.benchmark;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.vcore.datagenerator.SampleBsonDocumentGenerator;

public class Ingestion {
    
	/**
	 * The sample BSON document to be used for inserting documents into the vCore cluster for benchmarking.
	 * A new _id field is added prior to each insert operation.
	 */
    private Document sampleDocumentForIngestion;
    
    /**
     * The sample JSON string to be used to insert each Bson Document, 
     * after assigning a new _id value for each Document
     */
    private String jsonStringForSampleDocument;
    
    /**
     * The Bson Document Generator to generate sample documents to be inserted for the benchmarking run.
     */
    private SampleBsonDocumentGenerator documentGenerator;
    
    /**
     * 
     * @param documentGenerator
     * @param documentSize
     */
    public Ingestion(SampleBsonDocumentGenerator documentGenerator, int documentSize) {
    	this.documentGenerator = documentGenerator;
    	this.jsonStringForSampleDocument = this.documentGenerator.createSampleJsonDocument(documentSize);
    }
    
    /**
     * Inserts the pre-created sample Bson document after assigning a new _id value prior to each insert operation
     * 
     * @param employeeCollection The vCore collection into which data is inserted for benchmarking
     * @param sampleDocumentToIngest The sample Bson document to insert
     */
    public void insertDocuments(
    	MongoCollection<Document> employeeCollection,
    	int numDocumentsToinsert) {
        for (int eachDocumentToInsert = 0; eachDocumentToInsert < numDocumentsToinsert; eachDocumentToInsert++) {
        	
        	this.sampleDocumentForIngestion = Document.parse(this.jsonStringForSampleDocument);
        	this.sampleDocumentForIngestion.append("_id", new ObjectId());
        	employeeCollection.insertOne(this.sampleDocumentForIngestion);
        }        
    }
}
