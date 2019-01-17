package com.example.juridov.my_app_run.bootstrap;

import com.example.juridov.my_app_run.entity.Record;
import com.example.juridov.my_app_run.entity.User;
import com.example.juridov.my_app_run.repository.RecordRepository;
import com.example.juridov.my_app_run.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpringJpaBootstrap(RecordRepository recordRepository, UserRepository userRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createData();
    }

    private void createData() {
        //Add first user
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("123");
        long user1Id = userRepository.save(user1).getId();

        //Add second user
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("321");
        long user2Id = userRepository.save(user2).getId();

        //Add records for first user
        Record record1 = new Record();
        record1.setUserId(user1Id);
        record1.setDate(System.currentTimeMillis());
        record1.setTime(3);
        record1.setDistance(1000);
        recordRepository.save(record1);

        Record record2 = new Record();
        record2.setUserId(user1Id);
        record2.setDate(System.currentTimeMillis());
        record2.setTime(13);
        record2.setDistance(3000);
        recordRepository.save(record2);

        //Add records for second user
        Record record3 = new Record();
        record3.setUserId(user2Id);
        record3.setDate(System.currentTimeMillis());
        record3.setTime(7);
        record3.setDistance(2000);
        recordRepository.save(record3);

        Record record4 = new Record();
        record4.setUserId(user2Id);
        record4.setDate(System.currentTimeMillis());
        record4.setTime(20);
        record4.setDistance(5000);
        recordRepository.save(record4);

        Record record5 = new Record();
        record5.setUserId(user2Id);
        record5.setDate(System.currentTimeMillis());
        record5.setTime(2);
        record5.setDistance(1000);
        recordRepository.save(record5);
    }

}