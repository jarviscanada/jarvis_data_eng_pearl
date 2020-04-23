package ca.jrvs.apps.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject>{
    protected final Connection connection;
    protected final static String Last_Val = "SELECT last_value FROM ";
    protected final static String CUSTOMER_SEQUENCE = "hp_customer_seq";

    public DataAccessObject(Connection connection){
        super();
        this.connection=connection;
    }

    public abstract T findById (long ID);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete (long id);

    protected int getLastVal (String sequence){
        int key = 0;
        String sql = Last_Val + sequence;
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                key = rs.getInt(1);
            }
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static interface DataTransferObject {
        long getId();
    }
}
