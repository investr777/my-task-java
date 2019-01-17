package com.example.juridov.my_app_run.service;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;

    public Iterable<Record> getAllRecord() {
        return recordRepository.findAll();
    }

    public void addRecord(Record record) {
        recordRepository.save(record);
    }

    public void updateRecord(Record record, Long id) {
        Record recordFromDB = recordRepository.findRecordById(id);
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

    public void delete(Long id) {
        if (recordRepository.findRecordById(id) == null){
            return;
        }
        recordRepository.delete(recordRepository.findRecordById(id));
    }

    public Iterable<Record> getRecordsOfUser(Long id){
//        Set<Record> records = getAllRecord();
        return recordRepository.findAllByUserId(id);
    }
}
