package com.example.juridov.my_app_run.repository;

import com.example.juridov.my_app_run.entity.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {
    Record findRecordById(Long recordId);

    List<Record> findAllByUserId(Long userId);

}