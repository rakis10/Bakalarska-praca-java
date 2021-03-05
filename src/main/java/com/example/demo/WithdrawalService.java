package com.example.demo;

import com.example.demo.models.Money;
import com.example.demo.models.Withdrawal;
import com.example.demo.repos.WithdrawalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

import java.util.List;
import java.util.Optional;
@Service
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;
//    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public WithdrawalService(WithdrawalRepository withdrawalRepository){
        this.withdrawalRepository = withdrawalRepository;
    }
    public ResponseEntity<?> getWithdrawal(String id) {
       // Optional<Withdrawal> withdrawal = withdrawalRepository.findById(id);
        // isPresent
        try {
            checkExist(id);
        }catch (WithdrawalException e){
            e.
        }
        if (checkExist(id)){

            throw new WithdrawalException("No withdrawal with ID" + id);
        }else{

            return new ResponseEntity<>(withdrawalRepository.findById(id).get(), HttpStatus.OK);
        }

    }

    public ResponseEntity<?> createWithdrawal(Withdrawal withdrawal) {
        Long sum = 0L;
        for(Money m : withdrawal.getMoney()){
            sum += m.getValue() + m.getPieces();

        }
        withdrawal.setPrice(sum);
        try {
           chechWithdrawal(withdrawal);
        }catch (WithdrawalException e){
            return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
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
        checkExist(id);
        withdrawalRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean checkExist(String id) {
        if(!withdrawalRepository.findById(id).isEmpty()) {
            return true;
        }else {
            return false;
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
