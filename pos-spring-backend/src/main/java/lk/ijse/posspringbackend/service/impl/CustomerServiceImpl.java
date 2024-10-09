package lk.ijse.posspringbackend.service.impl;

import lk.ijse.posspringbackend.customStatusCode.SelectedErrorStatus;
import lk.ijse.posspringbackend.dao.CustomerDao;
import lk.ijse.posspringbackend.dto.CustomerStatus;
import lk.ijse.posspringbackend.dto.impl.CustomerDTO;
import lk.ijse.posspringbackend.entity.impl.CustomerEntity;
import lk.ijse.posspringbackend.exception.CustomerNotFoundException;
import lk.ijse.posspringbackend.exception.DataPersistException;
import lk.ijse.posspringbackend.service.CustomerService;
import lk.ijse.posspringbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity savedCustomer = customerDao.save(mapping.toCustomerEntity(customerDTO));
        if (savedCustomer == null) {
            throw new DataPersistException("Failed to save customer");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerEntity> allCustomers = customerDao.findAll();
        return mapping.asCustomerDtoList(allCustomers);
    }

    @Override
    public CustomerStatus getCustomer(String customerId) {
        if (customerDao.existsById(customerId)) {
            CustomerEntity selectedCustomer = customerDao.getReferenceById(customerId);
            CustomerDTO customerDTO = mapping.toCustomerDTO(selectedCustomer);
            return new SelectedErrorStatus(0, "Customer found", customerDTO,null,null);
        } else {
            return new SelectedErrorStatus(2, "Customer not found");
        }
    }



    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> existedCustomer = customerDao.findById(customerId);
        if (!existedCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer not found");
        } else {
            customerDao.deleteById(customerId);
        }
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        Optional<CustomerEntity> tempCustomer = customerDao.findById(customerId);
        if (tempCustomer.isPresent()) {
            tempCustomer.get().setName(customerDTO.getName());
            tempCustomer.get().setAddress(customerDTO.getAddress());
            tempCustomer.get().setContact(customerDTO.getContact());
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }
}
