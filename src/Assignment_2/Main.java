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
        BankAccount[] arr = new BankAccount[3];
        arr[0] = new BankAccount(1, "A", 100000);
        arr[1] = new BankAccount(2, "B", 200000);
        arr[2] = new BankAccount(3, "C", 300000);

        for (BankAccount a : arr) {
            System.out.println(a.username + " " + (int)a.balance);
        }

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

    static BankAccount findAccount(String name) {
        for (BankAccount a : accounts) {
            if (a.username.equals(name)) return a;
        }
        return null;
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
                System.out.println("1 Direct Add");
                System.out.println("2 Request (Queue)");
                int t = sc.nextInt();

                System.out.print("Enter username: ");
                String name = sc.next();
                System.out.print("Enter balance: ");
                double bal = sc.nextDouble();

                if (t == 1) {
                    accounts.add(new BankAccount(accounts.size() + 1, name, bal));
                    System.out.println("Account added successfully");
                } else {
                    accountRequests.add(new BankAccount(accounts.size() + 1, name, bal));
                    System.out.println("Request submitted");
                }
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
                BankAccount a = findAccount(name);
                if (a != null) {
                    System.out.println("Found: " + a.username + " " + (int)a.balance);
                } else {
                    System.out.println("Account not found");
                }
            }

            else if (ch == 4) {
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

            else if (ch == 5) {
                System.out.print("Enter username: ");
                String name = sc.next();
                System.out.print("Withdraw: ");
                double amount = sc.nextDouble();

                BankAccount a = findAccount(name);
                if (a != null) {
                    if (a.balance >= amount) {
                        a.balance -= amount;
                        history.push("Withdraw " + (int)amount + " from " + name);
                        System.out.println("New balance: " + (int)a.balance);
                    } else {
                        System.out.println("Not enough balance");
                    }
                } else {
                    System.out.println("Account not found");
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
                if (a != null) {
                    if (a.balance >= amount) {
                        a.balance -= amount;
                        history.push("Withdraw " + (int)amount + " from " + name);
                        System.out.println("New balance: " + (int)a.balance);
                    } else {
                        System.out.println("Not enough balance");
                    }
                } else {
                    System.out.println("Account not found");
                }
            }

            else if (ch == 3) return;
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("1 Process Account Requests");
            System.out.println("2 View Requests");
            System.out.println("3 View Bill Queue");
            System.out.println("4 Process Bill");
            System.out.println("5 Last Transaction");
            System.out.println("6 Undo");
            System.out.println("7 Back");

            int ch = sc.nextInt();

            if (ch == 1) {
                if (!accountRequests.isEmpty()) {
                    BankAccount a = accountRequests.poll();
                    accounts.add(a);
                    System.out.println("Account added: " + a.username);
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
                if (!history.isEmpty()) {
                    System.out.println("Last transaction: " + history.peek());
                } else {
                    System.out.println("No transactions");
                }
            }

            else if (ch == 6) {
                if (!history.isEmpty()) {
                    System.out.println("Undo → " + history.pop() + " removed");
                } else {
                    System.out.println("Nothing to undo");
                }
            }

            else if (ch == 7) return;
        }
    }
}