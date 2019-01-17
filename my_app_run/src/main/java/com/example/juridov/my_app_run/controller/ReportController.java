package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.report.Report;
import com.example.juridov.my_app_run.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Report> getRecordsOfWeeks() {
       return reportService.getReportOfRecords();
    }
}
