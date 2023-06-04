package project01;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin();
        admin.inputProductCodes();
        Set<String> productCodes = admin.getProductCodes();

        ExchangeMachine exchangeMachine = new ExchangeMachine(productCodes);
        exchangeMachine.run();
    }
}
