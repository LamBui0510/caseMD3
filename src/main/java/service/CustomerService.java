package service;

import config.dao.CustomerDao;
import model.Customer;

public class CustomerService {
    CustomerDao customerDao = new CustomerDao();

    public void add(Customer customer){
        customerDao.createStudentDao(customer);
        customerDao.showListCustomer();
    }

    public void edit(int id, Customer customer){
        customerDao.updateCustomer(id,customer);
        customerDao.showListCustomer();
    }

    public void delete(int id){
        customerDao.deleteCustomer(id);
        customerDao.showListCustomer();
    }
}
