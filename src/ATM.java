import java.util.Scanner;

class BankAccount{
    String Name;
    String UserName;
    String password;
    String Accountno;
    float balance = 100000f;
    int transaction = 0;
    String transactionhistory = "";

    //Registration
    public void register(){
        Scanner sc = new Scanner(System.in);

        //Take input
        System.out.println("\nEnter name : ");
        this.Name = sc.nextLine();

        System.out.println("\nEnter your username : ");
        this.UserName = sc.nextLine();

        System.out.println("\nEnter your password : ");
        this.password = sc.nextLine();

        System.out.println("\nEnter your account number");
        this.Accountno = sc.nextLine();

        System.out.println("\nRegistration completed... plz login\n");
    }

    // Login to the system
    public boolean login(){
        boolean flag = false;
        Scanner sc = new Scanner(System.in);

        // Take username
        while(flag==false){
            System.out.println("\nEnter username : ");
            String Username = sc.nextLine();

            //Cheak username
            if(Username.equals(UserName)){
                while(flag == false){
                    //Take password
                    System.out.println("\nEnter password : ");
                    String pass = sc.nextLine();

                    //Cheak password
                    if(pass.equals(password)){
                        System.out.println("\nLogin Successful");
                        flag = true;
                    }
                    else {
                        System.out.println("\nIncorrect password");
                    }
                }
            }
            else {
                System.out.println("\nUsername not found");
            }
        }
        return flag;
    }

    //Create withdraw function
    public void Withdraw(){
        System.out.println("\nEnter amount to withdraw");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        try{
            if (balance >= amount){
                transaction++;
                balance -= amount;
                System.out.println("\n Withdraw succesfully");

                //Create transaction history
                String str = amount + " Rs withdrawed\n";
                transactionhistory = transactionhistory.concat(str);
            }
            else{
                System.out.println("\nInsufficient balance");
            }
        }
        catch(Exception e){
        }
    }

    //Create deposite function
    public void Deposit(){
        System.out.println("\nEnter amount to deposite - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try{
            if(amount <= 20000f){
                transaction++;
                balance += amount;
                System.out.println("\nSuccesfully deposited");

                //Create transaction history
                String str = amount + " Rs deposited\n" ;
                transactionhistory = transactionhistory.concat(str);
            }
            else {
                System.out.println("\nSorry...Limit is 20000.0 Rs");
            }
        }
        catch (Exception e){
        }
    }

    //Create transfer function
    public void Transfer(){
        Scanner sc = new Scanner(System.in);

        //Take name of receipent
        System.out.println("\nEnter receipent name : ");
        String receipent = sc.nextLine();

        //Amount to transfer
        System.out.println("\nEnter amount to transfer :");
        float amount = sc.nextFloat();

        try{
            if(balance >= amount){
                if(amount <= 10000f){
                    transaction++;
                    balance -= amount;
                    System.out.println("\n Succesfully transfered to   " + receipent);

                    //Create transaction history
                    String str = amount + " Rs transfered to" + receipent + "\n";
                    transactionhistory = transactionhistory.concat(str);
                }
                else {
                    System.out.println("\n Sorry...Limit is 10000.00");
                }
            }
            else {
                System.out.println("\n Insufficient balance");
            }
        }
        catch (Exception e){

        }


    }
    // Cheak balance
    public void cheakBalance(){
        System.out.println("\n" + balance + "Rs");
    }

    // Transaction history
    public void transHis(){
        if(transaction == 0){
            System.out.println("\nEmpty");
        }
        else {
            System.out.println("\n" + transactionhistory);
        }
    }

}


public class ATM {

    //We use this function for taking proper choice
    public static int takeIntInput(int limit){
        int input = 0;
        boolean flag = false;

        while (flag == false){
            try{
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if(flag && input > limit || input<1){
                    System.out.println("Choose number between 1 to "+ limit);
                    flag = false;
                }
            }
            catch (Exception e){
                System.out.println("Enter only integer value");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args){
        System.out.println("------- Welcome to ATM -------");

        System.out.println("1 : Register \n2 : Exit");
        System.out.print("Enter your choice : ");
        int choice = takeIntInput(2);

        if(choice == 1){
            BankAccount b = new BankAccount();
            b.register();
            while (true){
                System.out.println("1 : Login\n2 : Exit");
                System.out.print("Enter your choice : ");
                int ch = takeIntInput(2);

                if(ch == 1){
                    if(b.login()){
                        System.out.println("\n--- Welcome Back ----");
                        boolean finishornot = false;
                        while(finishornot == false){
                            System.out.println("\n 1 : Withdraw \n 2 : Deposite \n 3 : Transfer \n 4 : Cheak Balance \n 5 : transaction history \n 6 : Quit\n");
                            System.out.print("Enter your choice : ");
                            int c = takeIntInput(6);

                            switch(c){
                                case 1:
                                    b.Withdraw();
                                    break;
                                case 2:
                                    b.Deposit();
                                    break;
                                case 3:
                                    b.Transfer();
                                    break;
                                case 4:
                                    b.cheakBalance();
                                    break;
                                case 5:
                                    b.transHis();
                                    break;
                                case 6:
                                    finishornot = true;
                                    //System.exit(0); //for directly exit
                                    break;
                            }
                        }
                    }
                }
                else {
                    System.exit(0);
                }
            }
        }
        else{
            System.exit(0);
        }
    }
}