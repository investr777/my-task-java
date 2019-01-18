package com.example.juridov.my_app_run.service;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void addRecord(Record record, Long userId) {
        record.setUserId(userId);
        recordRepository.save(record);
    }

    public void updateRecord(Record record, Long recordId) {
        Record recordFromDB = recordRepository.findRecordById(recordId);
        if (recordFromDB == null) {
            return;
        }
        if (record.getDistance() != null) {
            recordFromDB.setDistance(record.getDistance());
        }
        if (record.getTime() != null) {
            recordFromDB.setTime(record.getTime());
        }
        if (record.getDate() != null) {
            recordFromDB.setDate(record.getDate());
        }
        recordRepository.save(recordFromDB);
    }

    public void delete(Long recordId) {
        Record recordFromDB = recordRepository.findRecordById(recordId);
        if (recordFromDB == null) {
            return;
        }
        recordRepository.delete(recordFromDB);
    }

    public Iterable<Record> getRecordsOfUser(Long recordId) {
        return recordRepository.findAllByUserId(recordId);
    }
}