package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order implements DataTransferObject {
    private long id;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private Date createDate;
    private BigDecimal totalDue;
    private String status;
    private String salespersonFirstName;
    private String salespersonLastName;
    private String salespersonEmail;
    private List<OrderLine> orderLines;

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }

    public String getStatus() {
        return status;
    }

    public String getSalespersonFirstName() {
        return salespersonFirstName;
    }

    public String getSalespersonLastName() {
        return salespersonLastName;
    }

    public String getSalespersonEmail() {
        return salespersonEmail;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSalespersonFirstName(String salespersonFirstName) {
        this.salespersonFirstName = salespersonFirstName;
    }

    public void setSalespersonLastName(String salespersonLastName) {
        this.salespersonLastName = salespersonLastName;
    }

    public void setSalespersonEmail(String salespersonEmail) {
        this.salespersonEmail = salespersonEmail;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", createDate=" + createDate +
                ", totalDue=" + totalDue +
                ", status='" + status + '\'' +
                ", salespersonFirstName='" + salespersonFirstName + '\'' +
                ", salespersonLastName='" + salespersonLastName + '\'' +
                ", salespersonEmail='" + salespersonEmail + '\'' +
                ", orderLines=" + orderLines +
                '}';
    }

    @Override
    public long getId() {
        return id;
    }
}
