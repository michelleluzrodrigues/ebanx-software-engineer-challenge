package com.ebax.challenge.service;

import com.ebax.challenge.model.Account;
import com.ebax.challenge.model.EventRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    private final Map<String, Account> accounts = new HashMap<>();

    public void reset() {
        accounts.clear();
    }

    public Integer getBalance(String id) {
        Account acc = accounts.get(id);
        if (acc != null) {
            return acc.getBalance();
        }
        return null;
    }

    public ResponseEntity<Object> processEvent(EventRequest req) {
        String type = req.getType();

        if ("deposit".equals(type)) {
            return handleDeposit(req.getDestination(), req.getAmount());
        } else if ("withdraw".equals(type)) {
            return handleWithdraw(req.getOrigin(), req.getAmount());
        } else if ("transfer".equals(type)) {
            return handleTransfer(req.getOrigin(), req.getDestination(), req.getAmount());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    private ResponseEntity<Object> handleDeposit(String destinationId, int amount) {
        Account account = accounts.get(destinationId);

        if (account == null) {
            account = new Account(destinationId, amount);
            accounts.put(destinationId, account);
        } else {
            account.deposit(amount);
        }

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> destination = new HashMap<>();
        destination.put("id", account.getId());
        destination.put("balance", account.getBalance());
        response.put("destination", destination);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private ResponseEntity<Object> handleWithdraw(String originId, int amount) {
        Account account = accounts.get(originId);

        if (account == null || !account.withdraw(amount)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> origin = new HashMap<>();
        origin.put("id", account.getId());
        origin.put("balance", account.getBalance());
        response.put("origin", origin);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private ResponseEntity<Object> handleTransfer(String originId, String destinationId, int amount) {
        Account origin = accounts.get(originId);

        if (origin == null || !origin.withdraw(amount)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }

        Account destination = accounts.get(destinationId);
        if (destination == null) {
            destination = new Account(destinationId, amount);
            accounts.put(destinationId, destination);
        } else {
            destination.deposit(amount);
        }

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> originMap = new HashMap<>();
        originMap.put("id", origin.getId());
        originMap.put("balance", origin.getBalance());
        response.put("origin", originMap);

        Map<String, Object> destMap = new HashMap<>();
        destMap.put("id", destination.getId());
        destMap.put("balance", destination.getBalance());
        response.put("destination", destMap);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
