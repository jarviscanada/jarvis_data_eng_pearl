package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {

    private static final String INSERT = "INSERT INTO customer (first_name, last_name, "+
            "email, phone, address, city, state, zipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String GET_ONE = "SELECT customer_id, first_name, last_name, "+
            "email, phone, address, city, state, zipCode FROM customer WHERE customer_id=?";

    public static final String UPDATE = "UPDATE customer SET first_name=?, last_name=?, "+
            "email=?, phone=?, address=?, city=?, state=?, zipCode=? WHERE customer_id=?";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    public static final String DELETE = "DELETE FROM customer WHERE customer_id=?";

    @Override
    public Customer findById(long ID) {
        Customer customer = new Customer();
        try(PreparedStatement pstmt = this.connection.prepareStatement(GET_ONE);) {
            pstmt.setLong(1, ID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                customer.setId(rs.getLong("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZipCode(rs.getString("zipCode"));
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException(sqle);
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer update(Customer dto) {
        Customer customer = null;
        try(PreparedStatement pstmt = this.connection.prepareStatement(UPDATE);) {
            pstmt.setString(1, dto.getFirstName());
            pstmt.setString(2, dto.getLastName());
            pstmt.setString(3, dto.getEmail());
            pstmt.setString(4, dto.getPhone());
            pstmt.setString(5, dto.getAddress());
            pstmt.setString(6, dto.getCity());
            pstmt.setString(7, dto.getState());
            pstmt.setString(8, dto.getZipCode());
            pstmt.setLong(9, dto.getId());
            pstmt.execute();
            customer = this.findById(dto.getId());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException(sqle);
        }
        return customer;
    }

    @Override
    public Customer create(Customer dto) {
        try (PreparedStatement pstmt = this.connection.prepareStatement(INSERT);){
            pstmt.setString(1, dto.getFirstName());
            pstmt.setString(2, dto.getLastName());
            pstmt.setString(3, dto.getEmail());
            pstmt.setString(4, dto.getPhone());
            pstmt.setString(5, dto.getAddress());
            pstmt.setString(6, dto.getCity());
            pstmt.setString(7, dto.getState());
            pstmt.setString(8, dto.getZipCode());
            pstmt.execute();
            int id = this.getLastVal(CUSTOMER_SEQUENCE);
            return this.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try(PreparedStatement pstmt = this.connection.prepareStatement(DELETE);) {
            pstmt.setLong(1, id);
            pstmt.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException(sqle);
        }
    }
}

