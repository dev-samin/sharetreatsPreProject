package project02;

import java.util.LinkedList;

public class Customer {
    private int wallet;
    private LinkedList<Product> productTypeA;
    private LinkedList<Product> productTypeB;

    public Customer() {
        wallet = 10000;
        productTypeA = new LinkedList<>();
        productTypeB = new LinkedList<>();
    }


    public boolean insertCoin(){
        if (wallet < 100){
            return false;
        }
        wallet -= 100;
        return true;

    }
    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public LinkedList<Product> getProductTypeA() {
        return productTypeA;
    }

    public void setProductTypeA(LinkedList<Product> productTypeA) {
        this.productTypeA = productTypeA;
    }

    public LinkedList<Product> getProductTypeB() {
        return productTypeB;
    }

    public void setProductTypeB(LinkedList<Product> productTypeB) {
        this.productTypeB = productTypeB;
    }
}
