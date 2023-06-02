package project02;

public class Product {
    private String productName;
    private String grade;
    private String expirationDate;

    public Product(String productName, String grade, String expirationDate) {
        this.productName = productName;
        this.grade = grade;
        this.expirationDate = expirationDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
