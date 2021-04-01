package com.example.demo;

import com.example.demo.models.Money;
import com.example.demo.models.Withdrawal;
import com.example.demo.repos.WithdrawalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

import java.util.*;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;
//    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public WithdrawalService(WithdrawalRepository withdrawalRepository){
        this.withdrawalRepository = withdrawalRepository;
    }
    public ResponseEntity<?> getWithdrawal(String id) {
        Withdrawal w ;
        try {
            checkExist(id);
            w=  withdrawalRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("No withdrawal with ID "+ id, HttpStatus.NOT_FOUND);
        }
        catch (WithdrawalException e){
            System.out.println(e.toString());
            return new ResponseEntity<>("No withdrawal with ID + "+ id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(w, HttpStatus.OK);


    }

    public ResponseEntity<?> createWithdrawal(Withdrawal withdrawal) {

        int id = Integer.parseInt(withdrawalRepository.findTopByOrderByIdDesc().getId())  +1 ;
        System.out.println(id);
        withdrawal.setId(String.valueOf(id));

        Long sum = 0L;
        for(Money m : withdrawal.getMoney()){
            sum += m.getValue() + m.getPieces();

        }
        withdrawal.setPrice(sum);
        try {

           chechWithdrawal(withdrawal);
        }catch (WithdrawalException e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(withdrawal, HttpStatus.NOT_ACCEPTABLE);
        }
        withdrawalRepository.save(withdrawal);
        return new ResponseEntity<>(withdrawal, HttpStatus.CREATED);
    }

    public List<Withdrawal> getWithdrawals() {
        try {
           List<Withdrawal> withdrawals =  withdrawalRepository.findAll();
           for (Withdrawal w : withdrawals){
               chechWithdrawal(w);
           }
        }catch (WithdrawalException e){
            System.out.println(e.toString());
        }
        return withdrawalRepository.findAll();
    }

    public ResponseEntity<?> updateWithdrawal(String id, Withdrawal withdrawal) {
        withdrawal.setId(id);
        withdrawalRepository.save(withdrawal);
        return new ResponseEntity<>(withdrawal,HttpStatus.OK);
    }

    public ResponseEntity<?> deleteWithdrawal(String id) {

        try {
            checkExist(id);
        }catch (WithdrawalException e){
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        withdrawalRepository.deleteById(id);
        return new ResponseEntity<>("Withdrawal with ID: "+id +" deleted successfully",HttpStatus.OK);
    }

    private void checkExist(String id) {
        if(withdrawalRepository.findById(id).isEmpty()) {
            throw new WithdrawalException("Withdrawal with id: "+ id + " doesnt exist");
        }
    }


    private void chechWithdrawal(Withdrawal withdrawal){
            Long checksum = 0L;
            for (Money m : withdrawal.getMoney()
            ) {
                checksum += m.getValue() * m.getPieces();
            }

            if (checksum != withdrawal.getPrice() ){
                throw new WithdrawalException("Checksum of price failed");
            }

        }



}
