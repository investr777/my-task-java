package com.example.juridov.my_app_run.service;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {
    @Autowired
    private RecordRepository recordRepository;

    public List<ArrayList<Record>> getReportOfRecords() {
        List<Record> recordList = recordRepository.findAll();
        Map<Record, Integer> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();

        for (Record record : recordList) {
            calendar.setTimeInMillis(record.getDate());
            map.put(record, calendar.get(Calendar.WEEK_OF_YEAR));
        }
        Map<Integer, List<Record>> result = new HashMap<>();
        map.forEach((record, day) -> {
            result.computeIfAbsent(day, (k) -> new ArrayList<Record>()).add(record);
        });
        System.out.println(result);
        return null;
    }

//    public List<ArrayList<Record>> getReportOfRecords() {
//        List<Record> recordList = recordRepository.findAll();
//        Collections.sort(recordList, new Comparator<Record>() {
//            @Override
//            public int compare(Record o1, Record o2) {
//                return o1.getDate().compareTo(o2.getDate());
//            }
//        });
//        List<ArrayList<Record>> weeks = new ArrayList<>();
//        Calendar calendar = Calendar.getInstance();
//        int numberOfMonth = 1;
//        ArrayList<Record> records = new ArrayList<>();
//
//        for (int i = 0; i < recordList.size(); i++) {
//            calendar.setTimeInMillis(recordList.get(i).getDate());
//            System.out.println(recordList.get(i));
//            if (calendar.get(Calendar.WEEK_OF_YEAR) != numberOfMonth) {
//                records.clear();
//                numberOfMonth = calendar.get(Calendar.WEEK_OF_YEAR);
//            }
//            records.add(recordList.get(i));
//            weeks.add(records);
////            if (weeks.size() < 1) {
////                weeks.add(records);
////            }
////            if (weeks.size() >= numberOfMonth) {
////                weeks.set(numberOfMonth - 1, records);
////            }
//        }
//
//        return weeks;
//    }
}
