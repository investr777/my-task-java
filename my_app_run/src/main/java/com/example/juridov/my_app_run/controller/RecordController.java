package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Record> getAllRecords() {
        return recordService.getAllRecord();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRecord(@RequestBody Record record) {
        recordService.addRecord(record);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateRecord(@RequestBody Record record, @PathVariable Long id) {
        recordService.updateRecord(record, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRecord(@PathVariable Long id) {
        recordService.delete(id);
    }
}
