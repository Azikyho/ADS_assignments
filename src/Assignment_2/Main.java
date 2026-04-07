import java.util.*;

class BankAccount {
    int accountNumber;
    String username;
    double balance;

    BankAccount(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
}

public class Main {
    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        accounts.add(new BankAccount(1, "Ali", 150000));
        accounts.add(new BankAccount(2, "Sara", 220000));
        accounts.add(new BankAccount(3, "John", 100000));

        while (true) {
            System.out.println("1 – Enter Bank");
            System.out.println("2 – Enter ATM");
            System.out.println("3 – Admin Area");
            System.out.println("4 – Exit");

            int choice = sc.nextInt();

            if (choice == 1) bankMenu();
            else if (choice == 2) atmMenu();
            else if (choice == 3) adminMenu();
            else if (choice == 4) break;
        }
    }

    static void bankMenu() {
        while (true) {
            System.out.println("1 Add Account");
            System.out.println("2 Display Accounts");
            System.out.println("3 Search");
            System.out.println("4 Deposit");
            System.out.println("5 Withdraw");
            System.out.println("6 Bill Payment");
            System.out.println("7 Back");

            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Username: ");
                String name = sc.next();
                System.out.print("Balance: ");
                double bal = sc.nextDouble();
                accountRequests.add(new BankAccount(accounts.size() + 1, name, bal));
                System.out.println("Request submitted");
            }

            else if (ch == 2) {
                System.out.println("Accounts List:");
                int i = 1;
                for (BankAccount a : accounts) {
                    System.out.println(i++ + ". " + a.username + " – Balance: " + (int)a.balance);
                }
            }

            else if (ch == 3) {
                System.out.print("Enter username: ");
                String name = sc.next();
                for (BankAccount a : accounts) {
                    if (a.username.equals(name)) {
                        System.out.println("Found: " + a.username + " " + (int)a.balance);
                    }
                }
            }

            else if (ch == 4) {
                System.out.print("Enter username: ");
                String name = sc.next();
                System.out.print("Deposit: ");
                double amount = sc.nextDouble();

                for (BankAccount a : accounts) {
                    if (a.username.equals(name)) {
                        a.balance += amount;
                        history.push("Deposit " + (int)amount + " to " + name);
                        System.out.println("New balance: " + (int)a.balance);
                    }
                }
            }

            else if (ch == 5) {
                System.out.print("Enter username: ");
                String name = sc.next();
                System.out.print("Withdraw: ");
                double amount = sc.nextDouble();

                for (BankAccount a : accounts) {
                    if (a.username.equals(name)) {
                        a.balance -= amount;
                        history.push("Withdraw " + (int)amount + " from " + name);
                        System.out.println("New balance: " + (int)a.balance);
                    }
                }
            }

            else if (ch == 6) {
                System.out.print("Enter bill name: ");
                String bill = sc.next();
                billQueue.add(bill);
                System.out.println("Added: " + bill);
            }

            else if (ch == 7) return;
        }
    }

    static void atmMenu() {
        while (true) {
            System.out.println("1 Balance");
            System.out.println("2 Withdraw");
            System.out.println("3 Back");

            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Username: ");
                String name = sc.next();
                for (BankAccount a : accounts) {
                    if (a.username.equals(name)) {
                        System.out.println("Balance: " + (int)a.balance);
                    }
                }
            }

            else if (ch == 2) {
                System.out.print("Username: ");
                String name = sc.next();
                System.out.print("Amount: ");
                double amount = sc.nextDouble();

                for (BankAccount a : accounts) {
                    if (a.username.equals(name)) {
                        a.balance -= amount;
                        history.push("Withdraw " + (int)amount + " from " + name);
                        System.out.println("New balance: " + (int)a.balance);
                    }
                }
            }

            else if (ch == 3) return;
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("1 Process Account Requests");
            System.out.println("2 View Bill Queue");
            System.out.println("3 Process Bill");
            System.out.println("4 History");
            System.out.println("5 Undo");
            System.out.println("6 Back");

            int ch = sc.nextInt();

            if (ch == 1) {
                if (!accountRequests.isEmpty()) {
                    BankAccount a = accountRequests.poll();
                    accounts.add(a);
                    System.out.println("Account added: " + a.username);
                }
            }

            else if (ch == 2) {
                System.out.println("Queue:");
                for (String b : billQueue) {
                    System.out.println(b);
                }
            }

            else if (ch == 3) {
                if (!billQueue.isEmpty()) {
                    String b = billQueue.poll();
                    System.out.println("Processing: " + b);
                }
            }

            else if (ch == 4) {
                if (!history.isEmpty()) {
                    System.out.println("Last transaction: " + history.peek());
                }
            }

            else if (ch == 5) {
                if (!history.isEmpty()) {
                    System.out.println("Undo → " + history.pop() + " removed");
                }
            }

            else if (ch == 6) return;
        }
    }
}
