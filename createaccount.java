public class createaccount implements account{
    String password;
    String username;
    int balance=0;

    public createaccount(String username, String password){
        this.username=username;
        this.password=password;
    }

    @Override
    public void viewBalance() {
        System.out.println("balance:"+ balance);
    }

    @Override
    public void deposit(int amount){
        this.balance+=amount;
        viewBalance();
    }

    @Override
    public void withdraw(int amount){
        if(this.balance>=amount){
            this.balance-=amount;
        }
        else{
            System.out.println("unable to perform this transaction as insufficient funds are available");
        }
        viewBalance();

    }

    @Override
    public void transfer(int amount,createaccount payee){

        if(this.balance>=amount){
            this.balance-=amount;
            payee.balance+=amount;
        }
        else{
            System.out.println("you do not have enough money to perform this transaction");
        }

    }
    public static void menu(){
        System.out.println("PRESS:");
        System.out.println("1 to view balance");
        System.out.println("2 to deposit funds");
        System.out.println("3 to withdraw funds");
        System.out.println("4 to transfer funds");
        System.out.println("5 to logout");
    }

}
