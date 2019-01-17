package com.example.juridov.my_app_run.controller;

import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.entity.report.Report;
import com.example.juridov.my_app_run.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Report API", description = "Report REST Controller API, reports of the user by weeks")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation(value = "Get reports of the user by week", response = Report.class)
    @RequestMapping(value = "/by_weeks", method = RequestMethod.GET)
    public List<Report> getRecordsOfWeeks(@AuthenticationPrincipal User user) {
        return reportService.getReportOfRecords(user.getId());
    }
}
