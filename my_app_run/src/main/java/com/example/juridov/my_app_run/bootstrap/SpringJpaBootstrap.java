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
        Record record = new Record();
        record.setUserId(user1Id);
        record.setDate(System.currentTimeMillis());
        record.setTime(6);
        record.setDistance(1000);
        recordRepository.save(record);
        record.setId(0L);
        recordRepository.save(record);
        record.setId(0L);
        recordRepository.save(record);

        //TODO : добавить нормальнх двнных
    }

}