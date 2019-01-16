package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
public class RecordController {
    @Autowired
    private RecordRepository recordRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRecord(@RequestBody Record record) {
        recordRepository.save(record);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateRecord(@RequestBody Record record, @PathVariable Long id) {
        Record oldRecord = recordRepository.findRecordById(id);
        if (record.getDistance() != null)
            oldRecord.setDistance(record.getDistance());
        if (record.getTime() != null)
            oldRecord.setTime(record.getTime());
        if (record.getDate() != null)
            oldRecord.setDate(record.getDate());
        recordRepository.save(oldRecord);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRecord(@PathVariable Long id) {
        Record record = recordRepository.findRecordById(id);
        recordRepository.delete(record);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public Iterable<Record> getReportOfRecords(){
        return null;
    }
}
