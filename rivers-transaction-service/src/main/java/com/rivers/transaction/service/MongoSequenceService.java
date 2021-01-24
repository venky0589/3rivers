package com.rivers.transaction.service;

public interface MongoSequenceService {

	long getSequenceNumber(String sequenceName);
}
