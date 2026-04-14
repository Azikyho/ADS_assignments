import java.util.*;

public class Main {
    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Scanner sc = new Scanner(System.in);

    static BankAccount[] accountArray = new BankAccount[100];
    static int size = 0;

    public static void main(String[] args) {

        addAccount("Azeke", 150000);
        addAccount("Ilyas", 220000);
        addAccount("Ilya", 100000);

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

    static void addAccount(String name, double bal) {
        BankAccount a = new BankAccount(size + 1, name, bal);
        accounts.add(a);
        accountArray[size] = a;
        size++;
    }

    static BankAccount findAccount(String name) {
        for (BankAccount a : accounts) {
            if (a.username.equals(name)) return a;
        }
        return null;
    }

    static void bankMenu() {
        while (true) {
            System.out.println("1 Add Account Request");
            System.out.println("2 Display Accounts");
            System.out.println("3 Deposit");
            System.out.println("4 Withdraw");
            System.out.println("5 Add Bill");
            System.out.println("6 Back");

            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter username: ");
                String name = sc.next();
                System.out.print("Enter balance: ");
                double bal = sc.nextDouble();

                accountRequests.add(new BankAccount(0, name, bal));
                System.out.println("Request submitted");
            }

            else if (ch == 2) {
                int i = 1;
                for (BankAccount a : accounts) {
                    System.out.println(i++ + ". " + a.username + " – Balance: " + (int)a.balance);
                }
            }

            else if (ch == 3) {
                System.out.print("Enter username: ");
                String name = sc.next();
                System.out.print("Deposit: ");
                double amount = sc.nextDouble();

                BankAccount a = findAccount(name);
                if (a != null) {
                    a.balance += amount;
                    history.push("Deposit " + (int)amount + " to " + name);
                    System.out.println("New balance: " + (int)a.balance);
                } else {
                    System.out.println("Account not found");
                }
            }

            else if (ch == 4) {
                System.out.print("Enter username: ");
                String name = sc.next();
                System.out.print("Withdraw: ");
                double amount = sc.nextDouble();

                BankAccount a = findAccount(name);
                if (a == null) {
                    System.out.println("Account not found");
                } else if (a.balance < amount) {
                    System.out.println("Not enough balance");
                } else {
                    a.balance -= amount;
                    history.push("Withdraw " + (int)amount + " from " + name);
                    System.out.println("New balance: " + (int)a.balance);
                }
            }

            else if (ch == 5) {
                System.out.print("Enter bill: ");
                String bill = sc.next();
                billQueue.add(bill);
                System.out.println("Added: " + bill);
            }

            else if (ch == 6) return;
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
                BankAccount a = findAccount(name);
                if (a != null) {
                    System.out.println("Balance: " + (int)a.balance);
                } else {
                    System.out.println("Account not found");
                }
            }

            else if (ch == 2) {
                System.out.print("Username: ");
                String name = sc.next();
                System.out.print("Amount: ");
                double amount = sc.nextDouble();

                BankAccount a = findAccount(name);
                if (a == null) {
                    System.out.println("Account not found");
                } else if (a.balance < amount) {
                    System.out.println("Not enough balance");
                } else {
                    a.balance -= amount;
                    history.push("Withdraw " + (int)amount + " from " + name);
                    System.out.println("New balance: " + (int)a.balance);
                }
            }

            else if (ch == 3) return;
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("1 Process Account Request");
            System.out.println("2 View Requests");
            System.out.println("3 View Bill Queue");
            System.out.println("4 Process Bill");
            System.out.println("5 View Array");
            System.out.println("6 Last Transaction");
            System.out.println("7 Undo");
            System.out.println("8 Back");

            int ch = sc.nextInt();

            if (ch == 1) {
                if (!accountRequests.isEmpty()) {
                    BankAccount a = accountRequests.poll();
                    addAccount(a.username, a.balance);
                    System.out.println("Account created");
                } else {
                    System.out.println("No requests");
                }
            }

            else if (ch == 2) {
                for (BankAccount a : accountRequests) {
                    System.out.println(a.username + " " + (int)a.balance);
                }
            }

            else if (ch == 3) {
                for (String b : billQueue) {
                    System.out.println(b);
                }
            }

            else if (ch == 4) {
                if (!billQueue.isEmpty()) {
                    System.out.println("Processing: " + billQueue.poll());
                } else {
                    System.out.println("Queue empty");
                }
            }

            else if (ch == 5) {
                for (int i = 0; i < size; i++) {
                    System.out.println(accountArray[i].username + " " + (int)accountArray[i].balance);
                }
            }

            else if (ch == 6) {
                if (!history.isEmpty()) {
                    System.out.println("Last transaction: " + history.peek());
                } else {
                    System.out.println("No transactions");
                }
            }

            else if (ch == 7) {
                if (!history.isEmpty()) {
                    System.out.println("Undo → " + history.pop());
                } else {
                    System.out.println("Nothing to undo");
                }
            }

            else if (ch == 8) return;
        }
    }
}