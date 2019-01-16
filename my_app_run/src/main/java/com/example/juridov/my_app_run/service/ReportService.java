package com.example.juridov.my_app_run.service;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.report.Report;
import com.example.juridov.my_app_run.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {
    @Autowired
    private RecordRepository recordRepository;

    private Map<Integer, List<Record>> getRecordsOfWeeks() {
        List<Record> recordList = recordRepository.findAll();
        Map<Record, Integer> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();

        for (Record record : recordList) {
            calendar.setTimeInMillis(record.getDate());
            map.put(record, calendar.get(Calendar.WEEK_OF_YEAR));
        }
        Map<Integer, List<Record>> result = new HashMap<>();
        map.forEach((record, weekNumber) -> {
            result.computeIfAbsent(weekNumber, (k) -> new ArrayList<Record>()).add(record);
        });
        return result;
    }

    public List<Report> getReportOfRecords() {
        Double avSpeed = 0.0;
        Double avTime = 0.0;
        Integer totalDistance = 0;
        Map<Integer, List<Record>> map = getRecordsOfWeeks();
        List<Report> reportList = new ArrayList<>();
        for (Map.Entry<Integer, List<Record>> entry : map.entrySet()) {
            List<Record> records = entry.getValue();
            for (Record record : records) {
                avSpeed += record.getDistance() / record.getTime();
                avTime += record.getTime();
                totalDistance += record.getDistance();
            }
            avSpeed /= records.size();
            avTime /= records.size();
            reportList.add(new Report(avSpeed, avTime, totalDistance, entry.getKey()));
            avSpeed = 0.0;
            avTime = 0.0;
            totalDistance = 0;
        }
        return reportList;
    }
}
