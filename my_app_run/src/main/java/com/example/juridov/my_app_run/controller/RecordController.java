package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/records", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Record API", description = "Record REST Controller API, CRUD records")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @ApiOperation(value = "Get all records of the user", response = Record.class)
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Record> getAllRecords(@AuthenticationPrincipal User user) {
        return recordService.getRecordsOfUser(user.getId());
    }

    @ApiOperation(value = "Create a new record of the user", response = Record.class)
    @RequestMapping(method = RequestMethod.POST)
    public void addRecord(@AuthenticationPrincipal User user, @RequestBody Record record) {
        recordService.addRecord(record, user.getId());
    }

    @ApiOperation(value = "Edit a record of the user", response = Record.class)
    @RequestMapping(value = "/{recordId}", method = RequestMethod.PUT)
    public void updateRecord(@RequestBody Record record, @PathVariable Long recordId) {
        recordService.updateRecord(record, recordId);
    }

    @ApiOperation(value = "Delete a record of the user", response = Record.class)
    @RequestMapping(value = "/{recordId}", method = RequestMethod.DELETE)
    public void deleteRecord(@PathVariable Long recordId) {
        recordService.delete(recordId);
    }
}