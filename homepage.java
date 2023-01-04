//import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class homepage{
// 29/12/22 12:47
    public static void options(createaccount hm,LinkedList<createaccount> ll){
//27/12/22 14:59
        System.out.println("WELCOME "+hm.username.toUpperCase());
        String hold,user;
        Scanner sc;
        int tmp=0,amount;
        while (tmp != 5) {
            createaccount.menu();
            sc = new Scanner(System.in);
            tmp = Integer.parseInt(sc.nextLine().trim());

            if (tmp > 5 || tmp < 1) {
                System.out.println("invalid option selected");
            }
            switch (tmp) {
                case 1 -> hm.viewBalance();
                case 2 -> {
                    System.out.println("enter an amount to deposit");
                    Scanner d = new Scanner(System.in);
                    hold = d.nextLine();
                    hm.deposit(Integer.parseInt(hold));
                }
                case 3 -> {
                    System.out.println("enter an amount to withdraw");
                    Scanner d = new Scanner(System.in);
                    hold = d.nextLine();
                    hm.withdraw(Integer.parseInt(hold));
                }
                case 4 -> {
                    System.out.println("enter the name of the account holder you would like to pay");
                    Scanner n = new Scanner(System.in);
                    user=n.nextLine();

                    if(exists(user,ll)){
                        System.out.println("enter the amount you would like to transfer");
                        Scanner g = new Scanner(System.in);
                        amount= Integer.parseInt(g.nextLine());

                        ll.get(location(hm.username,ll)).transfer(amount,ll.get(location(user,ll)));
                    }else{
                        System.out.println("this user does not exist");
                    }

                }

            }
        }
    }
    public static void login(LinkedList<createaccount> ll){//taking in ll so new linked list isn't created each time

        String switching;
        do {//do while loop to handle invalid input
            loginOption();
            Scanner log = new Scanner(System.in);
            switching = log.nextLine();
            if(!(switching.equals("1"))&&!(switching.equals("2"))){
                System.out.println("invalid input detected");
            }
        }while(!(switching.equals("1"))&&!(switching.equals("2")));

        String user, password;
        int tracking=0,element=0;

        switch(switching) {//switch statement to handle if user is creating an account or logging in
            case "1" -> {
                System.out.println("Choose a username to create your account");
                Scanner s = new Scanner(System.in);
                user = s.nextLine();

                if (!exists(user,ll)){
                    do {
                        System.out.println("enter your 6 digit password now");
                        Scanner pass = new Scanner(System.in);
                        password = pass.nextLine();
                        if(password.length()!=6){
                            System.out.println("incorrect input");
                        }
                    }while(password.length()!=6);
                    createaccount h = new createaccount(user, password);
                    ll.add(h);//adding to linked list only if user is being created

                    options(h,ll);
                }

            }
            case "2" -> {
                System.out.println("enter your username");
                Scanner c = new Scanner(System.in);
                user = c.nextLine();
                for (createaccount temp : ll) {//checking if account exists
                    if (!(temp.username.equals(user))) {
                        tracking++;//if tracking ends up being equal to the size of the linked list, then all account have been checked and the account does not exist
                        element++;//returns index of account if found in the linked list
                        if (tracking == ll.size()) {
                            System.out.println("this account does not exist");
                            tracking = 0;//resetting tracking
                        }
                    } else {//else user is found
                        do {
                            System.out.println("enter your password");
                            Scanner in = new Scanner(System.in);
                            password = in.nextLine();
                            if(!password.equals(ll.get(element).password)){
                                System.out.println("incorrect password");
                            }
                        }while(!password.equals(ll.get(element).password));
                            options(ll.get(element), ll);

                        break;
                    }
                }


            }
        }

    }
    public static void loginOption(){
        System.out.println("press 1 to create an account");
        System.out.println("press 2 to login to an existing account");
    }
    public static void logoutOption(){
        System.out.println("would you like to exit the application?");
        System.out.println("Select 1 to exit\nSelect 2 to return to the menu");
    }
    public static void listing(){
//        29/12/22 00:27
        LinkedList<createaccount> ll= new LinkedList<>();
        boolean program = true;
        System.out.println("Thank you for choosing to bank with us💩");
        String logged;
        while(program) {
            login(ll);
            Scanner exit;
            do {
                logoutOption();
                exit = new Scanner(System.in);
                logged= exit.nextLine();
                if(!logged.equals("1")&&!logged.equals("2")){
                    System.out.println("invalid input detected");
                }
            }while(!logged.equals("1")&&!logged.equals("2"));
            if(logged.equals("1")){
                program=false;
            }
        }
    }
    public static boolean exists(String user,LinkedList<createaccount> ll){
        int count =0;
        for (createaccount temp : ll) {//checking if account exists
            if (user.equals(temp.username)) {
                count++;
            }
            if (count > 1) {//if more than one account exists
                System.out.println("account already exists");
            }

        }
        return  count>0;
    }
    public static int location(String user,LinkedList<createaccount> ll){
        int index=0;
        for (createaccount temp : ll) {//getting account index in linked list
            if(!user.equals(temp.username)){
                index++;
            }
            else{
                return index;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        listing();
    }

}
