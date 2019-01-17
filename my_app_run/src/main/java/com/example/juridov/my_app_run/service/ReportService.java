package com.example.juridov.my_app_run.service;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.entity.report.Report;
import com.example.juridov.my_app_run.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    private final RecordRepository recordRepository;

    @Autowired
    public ReportService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Report> getReportOfRecords(Long id) {
        Map<Integer, List<Record>> map = getRecordsOfWeeks(id);
        List<Report> reportList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Map.Entry<Integer, List<Record>> entry : map.entrySet()) {
            double avSpeed = 0.0;
            double avTime = 0.0;
            int totalDistance = 0;
            List<Record> records = entry.getValue();
            for (Record record : records) {
                avSpeed += (double) record.getDistance() / record.getTime();
                avTime += record.getTime();
                totalDistance += record.getDistance();
            }
            Report report = new Report();
            report.setAvSpeed(avSpeed / records.size());
            report.setAvTime(avTime / records.size());
            report.setTotalDistance(totalDistance);
            report.setWeekNumber(entry.getKey());
            //TODO : REVIEW!!!
            calendar.setTimeInMillis(records.get(0).getDate());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.clear(Calendar.MINUTE);
            calendar.clear(Calendar.SECOND);
            calendar.clear(Calendar.MILLISECOND);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
            report.setWeekStart(calendar.getTimeInMillis());
            calendar.add(Calendar.DAY_OF_WEEK, 6);
            report.setWeekEnd(calendar.getTimeInMillis());
            reportList.add(report);
        }
        return reportList;
    }

    private Map<Integer, List<Record>> getRecordsOfWeeks(Long id) {
        List<Record> recordList = recordRepository.findAllByUserId(id);
        Map<Record, Integer> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();

        for (Record record : recordList) {
            calendar.setTimeInMillis(record.getDate());
            map.put(record, calendar.get(Calendar.WEEK_OF_YEAR));
        }
        Map<Integer, List<Record>> result = new HashMap<>();
        map.forEach((record, weekNumber) -> result.computeIfAbsent(weekNumber, (k) -> new ArrayList<Record>()).add(record));
        return result;
    }

}