package com.vcore.benchmark;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.vcore.datagenerator.SampleBsonDocumentGenerator;

public class DataIngestor implements Runnable {

    /**
     * The Bson Document Generator to generate sample documents to be inserted for the benchmarking run.
     */
	private SampleBsonDocumentGenerator documentGenerator;
	
	/**
	 * The average size of the BSON document for the benchmarking run.
	 */
	private int documentSize;
	
	/**
	 * The MongoDB vCore collection into which documents will be inserted for the benchmarking run.
	 */
	private MongoCollection<Document> employeeCollection;
	
	/**
	 * The number of documents for this specific thread to insert into the vCore cluster for the benchmarking run.
	 */
	private int numDocumentsToInsert;
	
	public DataIngestor(
		SampleBsonDocumentGenerator documentGenerator, 
		int documentSize, 
		MongoCollection<Document> employeeCollection,
		int numDocumentsToInsert) {
			
		this.documentGenerator = documentGenerator;
			this.documentSize = documentSize;
			this.employeeCollection = employeeCollection;
			this.numDocumentsToInsert = numDocumentsToInsert;
	}
	
	@Override
	public void run() {
		System.out.println("Entering Thread: " + Thread.currentThread().getName());
		
		Ingestion vCoreIngestion = new Ingestion(documentGenerator, documentSize);
		vCoreIngestion.insertDocuments(this.employeeCollection, this.numDocumentsToInsert);
	
		System.out.println("Completed Execution of Thread: " + Thread.currentThread().getName());
	}
}
