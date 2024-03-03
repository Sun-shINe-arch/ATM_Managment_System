import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Creating_Account extends JFrame implements ActionListener {
    private static final String url = "jdbc:mysql://localhost:3306/atm";
    private static final String username = "root";
    private static final String password = "Crossroad@09";
    JButton button;
    JTextField text1, text2, text3, text4;
    JLabel label1, label2, label3, label4;

    void launchpage(String s) {
        JFrame frame = new JFrame();
        JLabel label = new JLabel(s);
        label.setBounds(25, 25, 300, 50);
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setForeground(Color.red);

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 125);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    Creating_Account() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(5, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(360, 40));
        panel1.setBackground(Color.lightGray);
        label1 = new JLabel();
        label1.setText("ACCOUNT  ");
        panel1.add(label1);
        text1 = new JTextField();
        text1.setPreferredSize(new Dimension(250, 30));
        text1.setFont(new Font("Consolas", Font.PLAIN, 25));
        text1.setBackground(Color.BLACK);
        text1.setForeground(Color.white);
        text1.setCaretColor(Color.RED);
        panel1.add(text1);
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(360, 40));
        panel2.setBackground(Color.lightGray);
        label2 = new JLabel();
        label2.setText("PIN             ");
        panel2.add(label2);
        text2 = new JTextField();
        text2.setPreferredSize(new Dimension(250, 30));
        text2.setFont(new Font("Consolas", Font.PLAIN, 25));
        text2.setBackground(Color.BLACK);
        text2.setForeground(Color.white);
        text2.setCaretColor(Color.RED);
        panel2.add(text2);
        add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(360, 40));
        panel3.setBackground(Color.lightGray);
        label3 = new JLabel();
        label3.setText("NAME       ");
        panel3.add(label3);
        text3 = new JTextField();
        text3.setPreferredSize(new Dimension(250, 30));
        text3.setFont(new Font("Consolas", Font.PLAIN, 25));
        text3.setBackground(Color.BLACK);
        text3.setForeground(Color.white);
        text3.setCaretColor(Color.RED);
        panel3.add(text3);
        add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(360, 40));
        panel4.setBackground(Color.lightGray);
        label4 = new JLabel();
        label4.setText("BALANCE ");
        panel4.add(label4);
        text4 = new JTextField();
        text4.setPreferredSize(new Dimension(250, 30));
        text4.setFont(new Font("Consolas", Font.PLAIN, 25));
        text4.setBackground(Color.BLACK);
        text4.setForeground(Color.white);
        text4.setCaretColor(Color.RED);
        panel4.add(text4);
        add(panel4);

        button = new JButton("Submit");
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 13));
        button.addActionListener(this);

        setResizable(false);
        add(button);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setEnabled(false);
            String acc_no = text1.getText();
            String pin = text2.getText();
            int pin_no = Integer.parseInt(pin);
            String name = text3.getText();
            String bal = text4.getText();
            Float balance = Float.parseFloat(bal);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException x) {
                x.printStackTrace();
            }
            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                String query = "INSERT INTO CLIENT VALUES(?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, acc_no);
                ps.setInt(2, pin_no);
                ps.setString(3, name);
                ps.setFloat(4, balance);
                int rowsaffected = ps.executeUpdate();
                if (rowsaffected > 0) {
                    launchpage("Account Created Successfully");
                } else {
                    launchpage("Account Not Created");
                }
            } catch (SQLException x) {
                System.out.println(x.getMessage());
            }
        }
    }
}

class Accessing_Account extends JFrame implements ActionListener {
    private static final String url = "jdbc:mysql://localhost:3306/atm";
    private static final String username = "root";
    private static final String password = "Crossroad@09";
    JFrame frame2, frame3, frame4;
    JButton button, button1, button2, button3, button4, button_m;
    JButton button_x, button_w, button_y, button_z;
    JTextField text1, text2, text_m;
    JLabel label1, label2;
    Float Bal = 0.0f;

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
                Part_Balance(Balance, A);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void Part_Balance(Float bal, String A) {
        JFrame frame3 = new JFrame();
        JLabel see = new JLabel("Balance of " + A + " : " + bal);
        see.setBounds(25, 25, 300, 50);
        see.setFont(new Font(null, Font.PLAIN, 20));
        see.setForeground(Color.BLUE);

        frame3.add(see);
        frame3.setBackground(Color.black);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(350, 125);
        frame3.setLayout(null);
        frame3.setVisible(true);
    }

    void Balance_Withdraw(String A, int P, int amount) {
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
                Bal = rs.getFloat("Balance");
            }
            Float newBalance = Bal + amount;
            String set = "UPDATE CLIENT SET Balance = ? WHERE Account_Number = ?";
            PreparedStatement ps = conn.prepareStatement(set);
            ps.setFloat(1, newBalance);
            ps.setString(2, A);
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
        Balance_Enquiry(A, P);
    }

    void Balance_Deposit(String A, int P, Float amount) {
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
                Bal = rs.getFloat("Balance");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Float newBalance = Bal + amount;
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
            ps.setString(2, A);
            int rowsaffected = ps.executeUpdate();
            if (rowsaffected > 0) {
                System.out.println("Balance Updated Successfully");
            } else {
                System.out.println("Balance not Updated");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Balance_Enquiry(A, P);
    }

    void read(String A, int P) {
        String Account;
        String Name;
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
                Account = rs.getString("Account_Number");
                Name = rs.getString("Name");
                Part2(Account, Name);
            }
        } catch (SQLException e) {
            System.out.println("Wrong Account Number OR PIN");
        }
    }

    public void Part2(String acc, String name) {
        frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(400, 250);
        frame2.setLayout(new GridLayout(6, 1, 10, 10));
        frame2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel lab1 = new JLabel();
        lab1.setPreferredSize(new Dimension(200, 17));
        lab1.setText("Name : " + name);
        lab1.setForeground(Color.BLACK);
        frame2.add(lab1);

        JLabel lab2 = new JLabel();
        lab2.setPreferredSize(new Dimension(200, 17));
        lab2.setText("Account Number : " + acc);
        lab2.setForeground(Color.BLACK);
        frame2.add(lab2);

        button1 = new JButton();
        button1.addActionListener(this);
        button1.setText("           Balance Enquiry           ");
        button1.setFocusable(false);
        button1.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame2.add(button1);

        button2 = new JButton();
        button2.addActionListener(this);
        button2.setText("                Withdraw                ");
        button2.setFocusable(false);
        button2.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame2.add(button2);

        button3 = new JButton();
        button3.addActionListener(this);
        button3.setText("                Deposit                 ");
        button3.setFocusable(false);
        button3.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame2.add(button3);

        button4 = new JButton();
        button4.addActionListener(this);
        button4.setText("                   Exit                  ");
        button4.setFocusable(false);
        button4.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame2.add(button4);

        frame2.setResizable(false);
        frame2.setVisible(true);
    }

    public void Part3() {
        frame3 = new JFrame();
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(400, 200);
        frame3.setLayout(new GridLayout(3, 1, 10, 10));
        frame3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel labx = new JLabel();
        labx.setPreferredSize(new Dimension(400, 17));
        labx.setText("-------------------------- WithDrawl Options --------------------------");
        labx.setForeground(Color.BLACK);
        labx.setFont(new Font("Comic Sans", Font.ITALIC, 15));
        frame3.add(labx);

        JLabel laby = new JLabel();
        laby.setPreferredSize(new Dimension(400, 17));
        laby.setText("                                                                       ");
        laby.setForeground(Color.BLACK);
        laby.setFont(new Font("Comic Sans", Font.ITALIC, 15));
        frame3.add(laby);

        button_x = new JButton();
        button_x.addActionListener(this);
        button_x.setText("         500        ");
        button_x.setFocusable(false);
        button_x.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame3.add(button_x);

        button_w = new JButton();
        button_w.addActionListener(this);
        button_w.setText("         1000        ");
        button_w.setFocusable(false);
        button_w.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame3.add(button_w);

        button_y = new JButton();
        button_y.addActionListener(this);
        button_y.setText("        2000       ");
        button_y.setFocusable(false);
        button_y.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame3.add(button_y);

        button_z = new JButton();
        button_z.addActionListener(this);
        button_z.setText("         EXIT        ");
        button_z.setFocusable(false);
        button_z.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame3.add(button_z);

        frame3.setResizable(false);
        frame3.setVisible(true);
    }

    public void Part4() {
        frame4 = new JFrame();
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setSize(400, 200);
        frame4.setLayout(new GridLayout(3, 1, 10, 10));
        frame4.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel panel_m = new JPanel();
        panel_m.setPreferredSize(new Dimension(360, 40));
        panel_m.setBackground(Color.lightGray);
        JLabel label_m = new JLabel();
        label_m.setText("Amount  ");
        panel_m.add(label_m);
        text_m = new JTextField();
        text_m.setPreferredSize(new Dimension(250, 30));
        text_m.setFont(new Font("Consolas", Font.PLAIN, 25));
        text_m.setBackground(Color.BLACK);
        text_m.setForeground(Color.white);
        text_m.setCaretColor(Color.RED);
        panel_m.add(text_m);
        frame4.add(panel_m);

        button_m = new JButton("Submit");
        button_m.setFocusable(false);
        button_m.setFont(new Font("Comic Sans", Font.BOLD, 13));
        button_m.addActionListener(this);

        frame4.setResizable(false);
        frame4.add(button_m);
        frame4.setVisible(true);
    }

    Accessing_Account() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(360, 40));
        panel1.setBackground(Color.lightGray);
        label1 = new JLabel();
        label1.setText("ACCOUNT  ");
        panel1.add(label1);
        text1 = new JTextField();
        text1.setPreferredSize(new Dimension(250, 30));
        text1.setFont(new Font("Consolas", Font.PLAIN, 25));
        text1.setBackground(Color.BLACK);
        text1.setForeground(Color.white);
        text1.setCaretColor(Color.RED);
        panel1.add(text1);
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(360, 40));
        panel2.setBackground(Color.lightGray);
        label2 = new JLabel();
        label2.setText("PIN             ");
        panel2.add(label2);
        text2 = new JTextField();
        text2.setPreferredSize(new Dimension(250, 30));
        text2.setFont(new Font("Consolas", Font.PLAIN, 25));
        text2.setBackground(Color.BLACK);
        text2.setForeground(Color.white);
        text2.setCaretColor(Color.RED);
        panel2.add(text2);
        add(panel2);

        button = new JButton("Submit");
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 13));
        button.addActionListener(this);

        setResizable(false);
        add(button);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setEnabled(false);
            String acc_no = text1.getText();
            String pin = text2.getText();
            int pin_no = Integer.parseInt(pin);
            read(acc_no, pin_no);
        }
        if (e.getSource() == button1) {
            button1.setEnabled(false);
            String acc_no = text1.getText();
            String pin = text2.getText();
            int pin_no = Integer.parseInt(pin);
            Balance_Enquiry(acc_no, pin_no);
        }
        if (e.getSource() == button2) {
            button2.setEnabled(false);
            Part3();
        }
        if (e.getSource() == button3) {
            button3.setEnabled(false);
            Part4();
        }
        if (e.getSource() == button4) {
            button4.setEnabled(false);
            System.exit(0);
        }
        if (e.getSource() == button_x) {
            button_x.setEnabled(false);
            String acc_no = text1.getText();
            String pin = text2.getText();
            int pin_no = Integer.parseInt(pin);
            Balance_Withdraw(acc_no, pin_no, 500);
        }

        if (e.getSource() == button_w) {
            button_w.setEnabled(false);
            String acc_no = text1.getText();
            String pin = text2.getText();
            int pin_no = Integer.parseInt(pin);
            Balance_Withdraw(acc_no, pin_no, 1000);
        }

        if (e.getSource() == button_y) {
            button_y.setEnabled(false);
            String acc_no = text1.getText();
            String pin = text2.getText();
            int pin_no = Integer.parseInt(pin);
            Balance_Withdraw(acc_no, pin_no, 2000);
        }

        if (e.getSource() == button_z) {
            button_z.setEnabled(false);
            System.exit(0);
        }
        if (e.getSource() == button_m) {
            button_m.setEnabled(false);
            String acc_no = text1.getText();
            String pin = text2.getText();
            int pin_no = Integer.parseInt(pin);
            String amo = text_m.getText();
            Float amount = Float.parseFloat(amo);
            Balance_Deposit(acc_no, pin_no, amount);
        }
    }
}

class Deleting_Account extends JFrame implements ActionListener {
    private static final String url = "jdbc:mysql://localhost:3306/atm";
    private static final String username = "root";
    private static final String password = "Crossroad@09";
    JTextField text1, text2;
    JButton button;

    void cleaning(String A, int P) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement state = conn.createStatement();
            String query = "DELETE FROM CLIENT WHERE Account_Number =" + A + " AND Pin_Number = " + P;
            int rowsaffected = state.executeUpdate(query);
            if (rowsaffected > 0) {
                ending(A);
            } else {
                System.out.println("Account not Deleted");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void ending(String A) {
        JFrame frame3 = new JFrame();
        JLabel see = new JLabel("Account " + A + " Deleted Successfully");
        see.setBounds(25, 15, 420, 50);
        see.setFont(new Font(null, Font.PLAIN, 20));
        see.setForeground(Color.RED);

        frame3.add(see);
        frame3.setBackground(Color.black);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(420, 100);
        frame3.setLayout(null);
        frame3.setVisible(true);
    }

    Deleting_Account() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 1, 10, 10));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(360, 40));
        panel1.setBackground(Color.lightGray);
        JLabel label1 = new JLabel();
        label1.setText("ACCOUNT  ");
        panel1.add(label1);
        text1 = new JTextField();
        text1.setPreferredSize(new Dimension(250, 30));
        text1.setFont(new Font("Consolas", Font.PLAIN, 25));
        text1.setBackground(Color.BLACK);
        text1.setForeground(Color.white);
        text1.setCaretColor(Color.RED);
        panel1.add(text1);
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(360, 40));
        panel2.setBackground(Color.lightGray);
        JLabel label2 = new JLabel();
        label2.setText("PIN             ");
        panel2.add(label2);
        text2 = new JTextField();
        text2.setPreferredSize(new Dimension(250, 30));
        text2.setFont(new Font("Consolas", Font.PLAIN, 25));
        text2.setBackground(Color.BLACK);
        text2.setForeground(Color.white);
        text2.setCaretColor(Color.RED);
        panel2.add(text2);
        add(panel2);

        button = new JButton("Submit");
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 13));
        button.addActionListener(this);

        setResizable(false);
        add(button);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setEnabled(false);
            String acc_no = text1.getText();
            String pin = text2.getText();
            int pin_no = Integer.parseInt(pin);
            cleaning(acc_no, pin_no);
        }
    }
}

public class ATM extends JFrame implements ActionListener {
    JButton button1, button2, button3, button4, button_m;
    JFrame frame;

    ATM() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(475, 250);
        frame.setLayout(new GridLayout(5, 1, 10, 25));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel lab1 = new JLabel();
        lab1.setPreferredSize(new Dimension(450, 40));
        lab1.setText("ATM TRANSACTION WINDOW");
        lab1.setForeground(Color.BLACK);
        lab1.setFont(new Font("Comic Sans", Font.ITALIC, 30));
        frame.add(lab1);

        button1 = new JButton();
        button1.addActionListener(this);
        button1.setText("          Creating Account           ");
        button1.setFocusable(false);
        button1.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame.add(button1);

        button2 = new JButton();
        button2.addActionListener(this);
        button2.setText("           Existing Account           ");
        button2.setFocusable(false);
        button2.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame.add(button2);

        button3 = new JButton();
        button3.addActionListener(this);
        button3.setText("           Deleting Account           ");
        button3.setFocusable(false);
        button3.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame.add(button3);

        button4 = new JButton();
        button4.addActionListener(this);
        button4.setText("                   Exit                  ");
        button4.setFocusable(false);
        button4.setFont(new Font("Comic Sans", Font.BOLD, 15));
        frame.add(button4);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ATM();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            button1.setEnabled(false);
            frame.dispose();
            new Creating_Account();
        }
        if (e.getSource() == button2) {
            button2.setEnabled(false);
            frame.dispose();
            new Accessing_Account();
        }
        if (e.getSource() == button3) {
            button3.setEnabled(false);
            frame.dispose();
            new Deleting_Account();
        }
        if (e.getSource() == button4) {
            System.exit(0);
        }
    }
}
