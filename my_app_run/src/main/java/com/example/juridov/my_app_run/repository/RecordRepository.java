package com.example.juridov.my_app_run.repository;

import com.example.juridov.my_app_run.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Record findRecordById (Long id);
}
