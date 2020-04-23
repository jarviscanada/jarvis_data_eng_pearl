package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor{
    public static void main(String[] args) {
        //System.out.println("Hello Learning JDBC");
        DatabaseConnectionManager dbcm = new DatabaseConnectionManager
                ("localhost", "hplussport",
                        "postgres", "password");
        try {
            Connection connection = dbcm.getConnection();
            //CustomerDAO customerDAO = new CustomerDAO(connection);
            /*Customer customer = new Customer();
            customer.setFirstName("Pearl");
            customer.setLastName("Guo");
            customer.setEmail("p7guo@uwaterloo.ca");
            customer.setPhone("(226)789-1286");
            customer.setAddress("1234 Main St");
            customer.setCity("Toronto");
            customer.setState("Ontario");
            customer.setZipCode("N2J 3W9");
            customerDAO.create(customer);*/

            /*Customer customer = customerDAO.findById(1000);
            System.out.println(customer.getFirstName()+" "+customer.getLastName());*/

            /*Customer customer = customerDAO.findById(10000);
            System.out.println(customer.getFirstName()+" "+customer.getLastName()+" "+
                    customer.getEmail());
            customer.setEmail("gwashington@wh.gov");
            customer = customerDAO.update(customer);
            System.out.println(customer.getFirstName()+" "+customer.getLastName()+" "+
                    customer.getEmail());*/

            /*Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Adams");
            customer.setEmail("jadams@wh.gov");
            customer.setPhone("(123)456-7891");
            customer.setAddress("1234 Main St");
            customer.setCity("Arlington");
            customer.setState("VA");
            customer.setZipCode("01234");
            Customer dbcustomer = customerDAO.create(customer);
            System.out.println(dbcustomer);
            dbcustomer = customerDAO.findById(dbcustomer.getId());
            System.out.println(dbcustomer);
            dbcustomer.setEmail("john.adams@wh.gov");
            dbcustomer = customerDAO.update(dbcustomer);
            System.out.println(dbcustomer);
            customerDAO.delete(dbcustomer.getId());*/

            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1000);
            System.out.println(order);
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}