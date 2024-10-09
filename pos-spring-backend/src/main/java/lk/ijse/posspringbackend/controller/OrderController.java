package lk.ijse.posspringbackend.controller;

import lk.ijse.posspringbackend.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/nextId", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getNextOrderId() {
        try {
            String nextOrderId = orderService.generateNextOrderId();
            return ResponseEntity.ok(nextOrderId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
