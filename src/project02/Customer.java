package project02;

public class Customer {
    private int wallet;

    public Customer() {
        wallet = 100;
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
            return true;
        }
        else {
            return false;
        }
    }
}
