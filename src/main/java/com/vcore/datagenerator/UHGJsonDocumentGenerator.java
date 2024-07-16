package com.vcore.datagenerator;

public class UHGJsonDocumentGenerator {
	
	private String jsonDocument = "{\r\n"
			+ "  \"id\": \"4100051736141\",\r\n"
			+ "  \"data\": \"\",\r\n"
			+ "  \"lastUpdated\": { \"sourceSystemTimestamp\": \"2023-08-18T14:31:02.491730Z\" },\r\n"
			+ "  \"security\": {\r\n"
			+ "    \"securityPermission\": [\r\n"
			+ "      { \"securityPermissionValue\": \"gps_read_np\" },\r\n"
			+ "      { \"securityPermissionValue\": \"gps_lob_sh_np\" }\r\n"
			+ "    ],\r\n"
			+ "    \"securityPermissionAny\": null,\r\n"
			+ "    \"securitySourceSystemCode\": \"gps\"\r\n"
			+ "  },\r\n"
			+ "  \"kafkaPartition\": 183,\r\n"
			+ "  \"individualIdentifier\": \"redacted\",\r\n"
			+ "  \"kafkaTimestamp\": \"2024-03-12T16:55:03.117Z\",\r\n"
			+ "  \"sourceIndividual\": null,\r\n"
			+ "  \"active\": true,\r\n"
			+ "  \"upsertTimestamp\": \"2024-04-24T23:15:50.349Z\",\r\n"
			+ "  \"kafkaOffset\": 1943672,\r\n"
			+ "  \"memberships\": [\r\n"
			+ "    {\r\n"
			+ "      \"sourceSystemAttributes\": { \"cdb\": null },\r\n"
			+ "      \"cancelReasonType\": null,\r\n"
			+ "      \"enrolleeSourceId\": null,\r\n"
			+ "      \"individualIdentifier\": \"4100051736141\",\r\n"
			+ "      \"membershipGroupData\": [\r\n"
			+ "        {\r\n"
			+ "          \"groupName\": \"PEOPLES HEALTH CHOICES 65\",\r\n"
			+ "          \"masterGroup\": \"100002\",\r\n"
			+ "          \"groupNumber\": \"78004\"\r\n"
			+ "        }\r\n"
			+ "      ],\r\n"
			+ "      \"legacyAttributes\": null,\r\n"
			+ "      \"eligibilitySystemType\": null,\r\n"
			+ "      \"coverageStatus\": null,\r\n"
			+ "      \"terminationDate\": null,\r\n"
			+ "      \"subscriberIndividualIdentifier\": null,\r\n"
			+ "      \"enrolleeMemberFacingIdentifier\": null,\r\n"
			+ "      \"claimSystemType\": { \"code\": \"2\", \"description\": \"COSMOS\" },\r\n"
			+ "      \"organizationIdentifier\": null,\r\n"
			+ "      \"subscriberMemberFacingIdentifier\": null,\r\n"
			+ "      \"customerAccountIdentifier\": null,\r\n"
			+ "      \"enrolleeSourceCode\": null,\r\n"
			+ "      \"plan\": { \"planTypeName\": \"POS\", \"planCode\": \"PH2\" },\r\n"
			+ "      \"areaGroup\": null,\r\n"
			+ "      \"packageBenefitPlanCode\": \"014\",\r\n"
			+ "      \"product\": null,\r\n"
			+ "      \"active\": true,\r\n"
			+ "      \"applicationIdentifier\": \"57361317\",\r\n"
			+ "      \"site\": \"PPL\",\r\n"
			+ "      \"productCode\": null,\r\n"
			+ "      \"membershipIdentifier\": \"113492372\",\r\n"
			+ "      \"hContractId\": \"H1961\",\r\n"
			+ "      \"customerAccount\": null,\r\n"
			+ "      \"memberMarketNumber\": null,\r\n"
			+ "      \"subscriberEnrolleeSourceId\": null,\r\n"
			+ "      \"effectiveDate\": \"2024-01-01\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"sourceSystemCode\": \"gps\"\r\n"
			+ "}";
	
	public UHGJsonDocumentGenerator() {
		
	}
	
	public String createSampleJsonDocument(int documentSize) {
		return this.jsonDocument;
	}
}
