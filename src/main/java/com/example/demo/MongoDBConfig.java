package com.example.demo;


import com.example.demo.models.*;
import com.example.demo.repos.UserRepository;
import com.example.demo.repos.WithdrawalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@EnableMongoRepositories(basePackageClasses = {UserRepository.class, })
@Configuration
public class MongoDBConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository) {
//        return strings -> {
//            userRepository.save(new User(1, "juraj" ,"foo" , "ROLE_USER"));
//            userRepository.save(new User(2, "foo","foo", "ROLE_ADMIN" ));
//        };
//    }
    @Bean
    CommandLineRunner commandLineRunner(WithdrawalRepository userRepository) {
    return strings -> {
        List m = new ArrayList<Money>() ;
        m.add(new Money(CashType.BANKNOTE,4,100));
        //userRepository.save(new Withdrawal(true,"123123123","21983192542", State.CREATED, 800L,  m ));
        userRepository.save(new Withdrawal(true,"1231231234","2198319254212", State.CREATED, 400L,  m ));
        //  userRepository.save(new Withdrawal(false, "Sam"));
    };
    }
}
