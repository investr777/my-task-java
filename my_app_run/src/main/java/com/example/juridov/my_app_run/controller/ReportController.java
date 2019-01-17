package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.report.Report;
import com.example.juridov.my_app_run.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Report> getRecordsOfWeeks(@AuthenticationPrincipal User user) {
       return reportService.getReportOfRecords(user.getId());
    }
}
