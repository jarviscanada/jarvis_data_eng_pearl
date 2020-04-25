package ca.jrvs.apps.jdbc;

import java.math.BigDecimal;

public class OrderLine {
    private int quantity;
    private String productCode;
    private String productName;
    private int productSize;
    private String productVariety;
    private BigDecimal productPrice;

    public int getQuantity() {
        return quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductSize() {
        return productSize;
    }

    public String getProductVariety() {
        return productVariety;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }

    public void setProductVariety(String productVariety) {
        this.productVariety = productVariety;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
