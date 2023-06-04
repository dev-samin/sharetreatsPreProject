package project02;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.inputProductCodes();
        LinkedList<Product> products = admin.getProducts();

        PoachingMachine poachingMachine = new PoachingMachine(products);
        poachingMachine.run();
    }
}
