package project02;

public class Customer {
    private int wallet;

    public Customer() {
        wallet = 10000;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public boolean insertCoin(){
        if (wallet >= 100){
            wallet -= 100;
            System.out.println("현재 남은 금액 " + wallet);
            return true;
        }
        else {
            System.out.println("금액이 부족합니다");
            System.out.println("현재 남은 금액 " + wallet);
            return false;
        }
    }
}
