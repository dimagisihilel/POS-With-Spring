package lk.ijse.posspringbackend.controller;

import lk.ijse.posspringbackend.dto.impl.OrderDetailDTO;
import lk.ijse.posspringbackend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order-detail")
@CrossOrigin
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            orderDetailService.saveOrderDetail(orderDetailDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
