package ru.msa.homework1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msa.homework1.model.StatusResponse;

@RestController
public class StatusController {

  @GetMapping("/health")
  public ResponseEntity<StatusResponse> getStatus() {
    return ResponseEntity.ok(new StatusResponse());
  }
}
