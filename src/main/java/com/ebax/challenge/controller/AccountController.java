package com.ebax.challenge.controller;

import com.ebax.challenge.model.EventRequest;
import com.ebax.challenge.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

        private final AccountService accountService;

        public AccountController(AccountService service) {
            this.accountService = service;
        }

        @PostMapping("/reset")
        public ResponseEntity<Void> reset() {
            accountService.reset();
            return ResponseEntity.ok().build();
        }

        @GetMapping("/balance")
        public ResponseEntity<Object> balance(@RequestParam("account_id") String id) {
            Integer balance = accountService.getBalance(id);
            if (balance == null) {
                return ResponseEntity.status(404).body(0);
            }
            return ResponseEntity.ok(balance);
        }

        @PostMapping("/event")
        public ResponseEntity<Object> event(@RequestBody EventRequest req) {
            return accountService.processEvent(req);
        }
}
