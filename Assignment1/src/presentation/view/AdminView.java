package presentation.view;

import presentation.controller.AdminController;

import service.BillService;
import service.TransferService;
import service.UserService;
import utils.DataConverter;

import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame {
    private static final long serialVersionUID=1L;
    private JPanel panel=new JPanel( new GridBagLayout());
    GridBagConstraints c=new GridBagConstraints();
    private AdminController controller=new AdminController(this);
    private JTable table=new JTable();
    private JTable table2=new JTable();
    private JTable table3=new JTable();
    private JLabel l1=new JLabel("First Name");
    private JLabel l2=new JLabel("Last Name");
    private JLabel l3=new JLabel("Username");
    private JLabel l4=new JLabel("Password");
    private JLabel l5=new JLabel("Admin/User");
    private JTextField t1=new JTextField(5);
    private JTextField t2=new JTextField(5);
    private JTextField t3=new JTextField(5);
    private JPasswordField t4=new JPasswordField(5);
    private JComboBox<Boolean> c1=new JComboBox<>();
    private JLabel l6=new JLabel("Start YYYY-MM-DD");
    private JLabel l7=new JLabel("End YYYY-MM-DD");
    private JTextField t5=new JTextField(5);
    private JTextField t6=new JTextField(5);
    private JButton b1=new JButton("View All");
    private JButton b2=new JButton("Add ");
    private JButton b3=new JButton("Update");
    private JButton b4=new JButton("Delete");
    private JButton b5=new JButton("Generate");
    private UserService userService;
    private TransferService transferService;
    private BillService billService;
    private DataConverter dataConverter;

    public AdminView(String name, UserService userService, TransferService transferService, BillService billService, DataConverter dataConverter){
        super(name);
        this.userService=userService;
        this.transferService=transferService;
        this.billService=billService;
        c.gridx=0;
        c.gridy=0;
        panel.add(l1,c);
        c.gridx=0;
        c.gridy=1;
        panel.add(l2,c);
        c.gridx=0;
        c.gridy=2;
        panel.add(l3,c);
        c.gridx=0;
        c.gridy=3;
        panel.add(l4,c);
        c.gridx=0;
        c.gridy=4;
        panel.add(l5,c);
        c.gridx=1;
        c.gridy=0;
        panel.add(t1,c);
        c.gridx=1;
        c.gridy=1;
        panel.add(t2,c);
        c.gridx=1;
        c.gridy=2;
        panel.add(t3,c);
        c.gridx=1;
        c.gridy=3;
        panel.add(t4,c);
        c.gridx=1;
        c.gridy=4;
        panel.add(c1,c);
        c1.addItem(true);
        c1.addItem(false);
        c.gridx=2;
        c.gridy=0;
        panel.add(b1,c);
        b1.addActionListener(controller);
        c.gridx=2;
        c.gridy=1;
        panel.add(b2,c);
        b2.addActionListener(controller);
        c.gridx=2;
        c.gridy=2;
        panel.add(b3,c);
        b3.addActionListener(controller);
        c.gridx=2;
        c.gridy=3;
        panel.add(b4,c);
        b4.addActionListener(controller);
        c.gridx=4;
        c.gridy=5;
        JScrollPane p=new JScrollPane(table);
        panel.add(p,c);
        c.gridx=3;
        c.gridy=0;
        panel.add(l6,c);
        c.gridx=3;
        c.gridy=1;
        panel.add(t5,c);
        c.gridx=3;
        c.gridy=2;
        panel.add(l7,c);
        c.gridx=3;
        c.gridy=3;
        panel.add(t6,c);
        c.gridx=3;
        c.gridy=4;
        panel.add(b5,c);
        b5.addActionListener(controller);
        JScrollPane pane2=new JScrollPane(table2);
        c.gridx=5;
        c.gridy=5;
        panel.add(pane2,c);
        this.dataConverter=dataConverter;
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);


    }



    public UserService getUserService() {
        return userService;
    }

    public DataConverter getDataConverter() {
        return dataConverter;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB3() {
        return b3;
    }

    public JButton getB4() {
        return b4;
    }

    public JButton getB2() {
        return b2;
    }



    public JTextField getT1() {
        return t1;
    }

    public JTextField getT2() {
        return t2;
    }

    public JTextField getT3() {
        return t3;
    }

    public JTextField getT4() {
        return t4;
    }

    public JComboBox<Boolean> getC1() {
        return c1;
    }

    public JTable getTable2() {
        return table2;
    }

    public JTextField getT5() {
        return t5;
    }

    public JTextField getT6() {
        return t6;
    }

    public JButton getB5() {
        return b5;
    }

    public JTable getTable3() {
        return table3;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public BillService getBillService() {
        return billService;
    }
}
