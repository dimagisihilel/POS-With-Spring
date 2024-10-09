package lk.ijse.posspringbackend.controller;

import lk.ijse.posspringbackend.customStatusCode.SelectedErrorStatus;
import lk.ijse.posspringbackend.dto.CustomerStatus;
import lk.ijse.posspringbackend.dto.impl.CustomerDTO;
import lk.ijse.posspringbackend.exception.CustomerNotFoundException;
import lk.ijse.posspringbackend.exception.DataPersistException;
import lk.ijse.posspringbackend.service.CustomerService;
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
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.saveCustomer(customerDTO);
            logger.info("Customer saved successfully: {}", customerDTO.getCustomerId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            logger.error("Error saving customer: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedCustomer(@PathVariable("customerId") String customerId) {
        String regexForCustomerId = RegexUtil.CUSTOMER_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForCustomerId);
        var regexMatcher = regexpattern.matcher(customerId);

        if (!regexMatcher.matches()) {
            logger.warn("Invalid customer ID format: {}", customerId);
            return new SelectedErrorStatus(1, "ID not valid");
        }
        logger.info("Retrieving customer with ID: {}", customerId);
        return customerService.getCustomer(customerId);
    }
    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        String regexForCustomerId = RegexUtil.CUSTOMER_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForCustomerId);
        var regexMatcher = regexpattern.matcher(customerId);
        try {
            if (!regexMatcher.matches()) {
                logger.warn("Invalid customer ID format: {}", customerId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerService.deleteCustomer(customerId);
            logger.info("Deleted customer with ID: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        logger.info("Retrieving all customers");
        return customerService.getAllCustomers();
    }
    @PutMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("customerId") String customerId) {
        String regexForCustomerId = RegexUtil.CUSTOMER_ID_REGEX;
        Pattern regexpattern = Pattern.compile(regexForCustomerId);
        var regexMatcher = regexpattern.matcher(customerId);
        try {
            if (!regexMatcher.matches()) {
                logger.warn("Invalid customer ID format: {}", customerId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerDTO.setCustomerId(customerId);
            customerService.updateCustomer(customerId, customerDTO);
            logger.info("Updated customer with ID: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            logger.error("Error updating customer: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
