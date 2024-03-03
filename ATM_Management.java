package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ATM_Management {
    Scanner input = new Scanner(System.in);
    private static final String url = "jdbc:mysql://localhost:3306/atm";
    private static final String username = "root";
    private static final String password = "Crossroad@09";
    static String reset = "\u001B[0m";
    static String red = "\u001B[31m";
    static String yellow = "\u001B[33m";
    static String white_b = "\u001B[47m";
    static String green = "\u001B[32m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void Display() {
        System.out.println(
                "\n\t\t\t\t\t    =============================================================================");
        System.out
                .println("\t\t\t\t\t    =============================================================================");
        System.out.println("\t\t\t\t\t                          ||   ATM TRANSCATION WINDOW   ||    ");
        System.out
                .println("\t\t\t\t\t    =============================================================================");
        System.out
                .println("\t\t\t\t\t    =============================================================================");
    }

    private int Display1() {
        System.out.println(green + white_b);
        System.out.println("\t\t\t\t\t\t\t    =========================================    ");
        System.out.println("\t\t\t\t\t\t\t    ||                                     ||    ");
        System.out.println("\t\t\t\t\t\t\t    ||   (1)New Customer                   ||    ");
        System.out.println("\t\t\t\t\t\t\t    ||   (2)Existing Customer              ||    ");
        System.out.println("\t\t\t\t\t\t\t    ||   (3)Delete Existing Account        ||    ");
        System.out.println("\t\t\t\t\t\t\t    ||   (4)Exit                           ||    ");
        System.out.println("\t\t\t\t\t\t\t    ||                                     ||    ");
        System.out.println("\t\t\t\t\t\t\t    =========================================    ");
        System.out.println(reset);
        System.out.print("Please Enter your choice : ");
        int a = input.nextInt();
        return a;
    }

    private int Administrator() {
        System.out.println("\t\t       ADMINISTRATOR MENU");
        System.out.println();
        System.out.println("\t\t |______1) Balance Enquiry___|");
        System.out.println("\t\t |                           |");
        System.out.println("\t\t |______2) Withdraw__________|");
        System.out.println("\t\t |                           |");
        System.out.println("\t\t |______3) Deposit___________|");
        System.out.println("\t\t |                           |");
        System.out.println("\t\t |______4) Exit______________|");
        System.out.println();
        System.out.print("Please Enter your choice : ");
        int a = input.nextInt();
        return a;
    }

    private int Withdraw() {
        System.out.println("\t\t---------------- WithDrawl Options ------------------- ");
        System.out.println("\t\t1 - 500 \t\t\t2 - 1000 ");
        System.out.println("\t\t3 - 2000\t\t\t4 - Cancel Transaction ");
        System.out.println("\nChoose a WithDrawl Option (1 - 4) ");
        int a = input.nextInt();
        return a;
    }

    static Float withdraw(Float amount, int fist) {
        Float temp;
        temp = amount - fist;
        return temp;
    }

    static Float deposit(Float amount, Float fist) {
        Float temp;
        temp = amount + fist;
        return temp;
    }

    static void Sleepy(int n) {
        try {
            Thread.sleep(n * 1000); // sleep for n second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void Balance_Enquiry(String A, int P) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement state = conn.createStatement();
            String query = "SELECT Balance FROM CLIENT WHERE Account_Number = " + A
                    + " AND Pin_Number = " + P;
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                Float Balance = rs.getFloat("Balance");
                System.out.println("Balance of " + A + " : " + Balance);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean read(String A, int P) {
        boolean cursor = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement state = conn.createStatement();
            String query = "SELECT * FROM CLIENT WHERE Account_Number = " + A + " AND Pin_Number = " + P;
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                cursor = true;
                String RESET = "\u001B[0m";
                String RED = "\u001B[31m";
                System.out.println(RED);
                String Account = rs.getString("Account_Number");
                String Name = rs.getString("Name");
                System.out.println("Account_Number : " + Account);
                System.out.println("NAME : " + Name);
                System.out.println(RESET);
            }
        } catch (SQLException e) {
            System.out.println("Wrong Account Number OR PIN");
        }
        if (cursor) {
            return true;
        }
        return false;
    }

    static void write(String s, String A, int P, Float f) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO CLIENT VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, A);
            ps.setInt(2, P);
            ps.setString(3, s);
            ps.setFloat(4, f);
            int rowsaffected = ps.executeUpdate();
            if (rowsaffected > 0) {
                System.out.println("Data Inserted Successfully");
            } else {
                System.out.println("Data not Inserted");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ATM_Management bright = new ATM_Management();
        Scanner inputx = new Scanner(System.in);
        while (true) {
            clearScreen();
            Display();
            int X = bright.Display1();
            if (X == 1) {
                System.out.print("Enter Your Account_Number : ");
                String a_num = inputx.nextLine();
                System.out.print("Enter Your Pin : ");
                int p_num = inputx.nextInt();
                inputx.nextLine();
                System.out.print("Enter Your Name : ");
                String nam = inputx.nextLine();
                System.out.print("Enter Amount to Deposit in Account " + a_num + " : ");
                Float balance = inputx.nextFloat();
                write(nam, a_num, p_num, balance);
                System.out.println("Your Account has Been Successfully Created ");
                try {
                    Thread.sleep(1000); // sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                main(args);
            }
            if (X == 2) {
                System.out.print("Enter your Account_Number : ");
                String a_num = inputx.nextLine();
                System.out.print("Enter Your Pin : ");
                int p_num = inputx.nextInt();
                boolean Verify = read(a_num, p_num);
                if (Verify == true) {
                    int K = bright.Administrator();
                    if (K == 1) {
                        Balance_Enquiry(a_num, p_num);
                    }
                    if (K == 2) {
                        Float Bal = 0.0f;
                        int M = bright.Withdraw();
                        if (M == 1) {
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            try {
                                Connection conn = DriverManager.getConnection(url, username, password);
                                Statement state = conn.createStatement();
                                String query = "SELECT Balance FROM CLIENT WHERE Account_Number = " + a_num
                                        + " AND Pin_Number = " + p_num;
                                ResultSet rs = state.executeQuery(query);
                                while (rs.next()) {
                                    Bal = rs.getFloat("Balance");
                                }
                                Float newBalance = withdraw(Bal, 500);
                                String set = "UPDATE CLIENT SET Balance = ? WHERE Account_Number = ?";
                                PreparedStatement ps = conn.prepareStatement(set);
                                ps.setFloat(1, newBalance);
                                ps.setString(2, a_num);
                                int rowsaffected = ps.executeUpdate();
                                if (rowsaffected > 0) {
                                    System.out.println("Balance Updated Successfully");
                                    System.out.println("Please, Collect your Money");
                                } else {
                                    System.out.println("Balance not Updated");
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            Balance_Enquiry(a_num, p_num);
                            Sleepy(2);
                        }
                        if (M == 2) {
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            try {
                                Connection conn = DriverManager.getConnection(url, username, password);
                                Statement state = conn.createStatement();
                                String query = "SELECT Balance FROM CLIENT WHERE Account_Number = " + a_num
                                        + " AND Pin_Number = " + p_num;
                                ResultSet rs = state.executeQuery(query);
                                while (rs.next()) {
                                    Bal = rs.getFloat("Balance");
                                }
                                Float newBalance = withdraw(Bal, 1000);
                                String set = "UPDATE CLIENT SET Balance = ? WHERE Account_Number = ?";
                                PreparedStatement ps = conn.prepareStatement(set);
                                ps.setFloat(1, newBalance);
                                ps.setString(2, a_num);
                                int rowsaffected = ps.executeUpdate();
                                if (rowsaffected > 0) {
                                    System.out.println("Balance Updated Successfully");
                                    System.out.println("Please, Collect your Money");
                                } else {
                                    System.out.println("Balance not Updated");
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            Balance_Enquiry(a_num, p_num);
                            Sleepy(2);
                        }
                        if (M == 3) {
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            try {
                                Connection conn = DriverManager.getConnection(url, username, password);
                                Statement state = conn.createStatement();
                                String query = "SELECT Balance FROM CLIENT WHERE Account_Number = " + a_num
                                        + " AND Pin_Number = " + p_num;
                                ResultSet rs = state.executeQuery(query);
                                while (rs.next()) {
                                    Bal = rs.getFloat("Balance");
                                }
                                Float newBalance = withdraw(Bal, 2000);
                                String set = "UPDATE CLIENT SET Balance = ? WHERE Account_Number = ?";
                                PreparedStatement ps = conn.prepareStatement(set);
                                ps.setFloat(1, newBalance);
                                ps.setString(2, a_num);
                                int rowsaffected = ps.executeUpdate();
                                if (rowsaffected > 0) {
                                    System.out.println("Balance Updated Successfully");
                                    System.out.println("Please, Collect your Money");
                                } else {
                                    System.out.println("Balance not Updated");
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            Balance_Enquiry(a_num, p_num);
                            Sleepy(2);
                        }
                        if (M == 4)
                            System.exit(0);
                    }
                    if (K == 3) {
                        Float Bal = 0.0f;
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            Connection conn = DriverManager.getConnection(url, username, password);
                            Statement state = conn.createStatement();
                            String query = "SELECT Balance FROM CLIENT WHERE Account_Number = " + a_num
                                    + " AND Pin_Number = " + p_num;
                            ResultSet rs = state.executeQuery(query);
                            while (rs.next()) {
                                Bal = rs.getFloat("Balance");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.print(green + white_b + "Enter Amount you Want to DEPOSIT : " + reset);
                        Float deposit = inputx.nextFloat();
                        Float newBalance = deposit(Bal, deposit);
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            Connection conn = DriverManager.getConnection(url, username, password);
                            String query = "UPDATE CLIENT SET Balance = ? WHERE Account_Number =?";
                            PreparedStatement ps = conn.prepareStatement(query);
                            ps.setFloat(1, newBalance);
                            ps.setString(2, a_num);
                            int rowsaffected = ps.executeUpdate();
                            if (rowsaffected > 0) {
                                System.out.println("Balance Updated Successfully");
                            } else {
                                System.out.println("Balance not Updated");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (K == 4)
                        System.exit(0);
                    else {
                        Sleepy(5);
                        main(args);
                    }
                }
            }
            if (X == 3) {
                System.out.print("Enter Your Account Number : ");
                String a_num = inputx.nextLine();
                System.out.print("Enter Your Pin : ");
                int p_num = inputx.nextInt();
                inputx.nextLine();
                System.out.println();
                System.out.print(red + "Are you Surely want to delete your Account(Yes/No) : " + reset);
                String ch = inputx.next();
                if (ch.equalsIgnoreCase("YES")) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        Connection conn = DriverManager.getConnection(url, username, password);
                        Statement state = conn.createStatement();
                        String query = "DELETE FROM CLIENT WHERE Account_Number =" + a_num;
                        int rowsaffected = state.executeUpdate(query);
                        if (rowsaffected > 0) {
                            System.out.println(yellow + "Account " + a_num + " Deleted Successfully" + reset);
                            Sleepy(5);
                        } else {
                            System.out.println("Account not Deleted");
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println(yellow);
                    System.out.println("You Entered Your Choice as 'NO'");
                    System.out.println("or You have Entered Incorrectly");
                    System.out.println(reset);
                    Sleepy((p_num % 10));
                    main(args);
                }
            }
            if (X == 4)
                System.exit(0);
            inputx.close();
        }
    }
}
