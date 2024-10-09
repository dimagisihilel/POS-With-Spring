package lk.ijse.posspringbackend.controller;

import lk.ijse.posspringbackend.customStatusCode.SelectedErrorStatus;
import lk.ijse.posspringbackend.dto.OrderStatus;
import lk.ijse.posspringbackend.dto.impl.OrderDTO;
import lk.ijse.posspringbackend.exception.DataPersistException;
import lk.ijse.posspringbackend.exception.OrderNotFoundException;
import lk.ijse.posspringbackend.service.OrderService;
import lk.ijse.posspringbackend.util.RegexUtil;
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
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/nextId", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getNextOrderId() {
        try {
            String nextOrderId = orderService.generateNextOrderId();
            return ResponseEntity.ok(nextOrderId);
        } catch (Exception e) {
            logger.error("Error generating next order ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO orderDTO) {
        try {
            orderService.saveOrder(orderDTO);
            logger.info("Order saved successfully: {}", orderDTO.getOrderId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            logger.error("Error saving order: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderStatus getSelectedOrder(@PathVariable("orderId") String orderId) {
        String regexForOrderId = RegexUtil.ORDER_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForOrderId);
        var regexMatcher = regexpattern.matcher(orderId);

        if (!regexMatcher.matches()) {
            logger.warn("Invalid order ID format: {}", orderId);
            return new SelectedErrorStatus(1, "ID not valid");
        }
        logger.info("Retrieving order with ID: {}", orderId);
        return orderService.getOrder(orderId);
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") String orderId) {
        String regexForOrderId = RegexUtil.ORDER_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForOrderId);
        var regexMatcher = regexpattern.matcher(orderId);

        try {
            if (!regexMatcher.matches()) {
                logger.warn("Invalid order ID format: {}", orderId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderService.deleteOrder(orderId);
            logger.info("Deleted order with ID: {}", orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            logger.error("Order not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAllOrders() {
        logger.info("Retrieving all orders");
        return orderService.getAllOrders();
    }

    @PutMapping(value = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable("orderId") String orderId) {
        String regexForOrderId = RegexUtil.ORDER_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForOrderId);
        var regexMatcher = regexpattern.matcher(orderId);

        try {
            if (!regexMatcher.matches()) {
                logger.warn("Invalid order ID format: {}", orderId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderDTO.setOrderId(orderId);
            orderService.updateOrder(orderId, orderDTO);
            logger.info("Updated order with ID: {}", orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            logger.error("Error updating order: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
