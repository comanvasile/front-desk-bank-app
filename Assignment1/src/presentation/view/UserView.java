package presentation.view;

import presentation.controller.UserController;
import service.*;
import utils.DataConverter;

import javax.swing.*;
import java.awt.*;

public class UserView extends JFrame {
    private static final long serialVersionUID=1L;
    private JPanel panel=new JPanel( new GridBagLayout());
    GridBagConstraints c=new GridBagConstraints();
    private UserController controller=new UserController(this);
    private DataConverter dataConverter;
    private AccountService accountService;
    private ClientService clientService;
    private JLabel l1=new JLabel("Name");
    private JLabel l2=new JLabel("ID Number");
    private JLabel l3=new JLabel("Address");
    private JLabel l4=new JLabel("CNP");
    private JLabel l6=new JLabel("Type");
    private JLabel l5=new JLabel("Amount");
    private JLabel l7=new JLabel("Account");
    private JTextField t1=new JTextField(5);
    private JTextField t2=new JTextField(5);
    private JTextField t3=new JTextField(5);
    private JTextField t4=new JTextField(5);
    private JTextField t5=new JTextField(5);
    private JComboBox<String> c1=new JComboBox<String>();
    private JButton b1=new JButton("Add");
    private JButton b2=new JButton("Update ");
    private JButton b3=new JButton("View");
    private JButton b4=new JButton("Delete");
    private JButton b5=new JButton("Pay/Transfer");
    private JCheckBox cb=new JCheckBox();
    private JTable table1=new JTable();
    private JTable table2=new JTable();
    private BillService billService;
    private TransferService transferService;
    private UtilityService utilityService;


    public UserView(String name, ClientService clientService, AccountService accountService, UtilityService utilityService, TransferService transferService,BillService billService, DataConverter dataConverter){
        super(name);
        this.clientService=clientService;
        this.accountService=accountService;
        this.utilityService=utilityService;
        this.billService=billService;
        this.transferService=transferService;
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
        c.gridx=3;
        c.gridy=0;
        panel.add(l5,c);
        c.gridx=3;
        c.gridy=1;
        panel.add(l6,c);
        c.gridx=3;
        c.gridy=2;
        panel.add(l7,c);
        c.gridx=4;
        c.gridy=0;
        panel.add(t5,c);
        c.gridx=4;
        c.gridy=1;
        c1.addItem("Spending Account");
        c1.addItem("Saving Account");
        panel.add(c1,c);
        c.gridx=4;
        c.gridy=2;
        panel.add(cb,c);
        c.gridx=4;
        c.gridy=3;
        panel.add(b5,c);
        b5.addActionListener(controller);
        c.gridx=5;
        c.gridy=4;
        JScrollPane pane=new JScrollPane(table1);
        panel.add(pane,c);
        c.gridx=6;
        c.gridy=4;
        JScrollPane pane2=new JScrollPane(table2);
        panel.add(pane2,c);

        this.dataConverter=dataConverter;
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public JButton getB5() {
        return b5;
    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTable getTable2() {
        return table2;
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

    public ClientService getClientService() {
        return clientService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public DataConverter getDataConverter() {
        return dataConverter;
    }

    public JTextField getT5() {
        return t5;
    }

    public JComboBox<String> getC1() {
        return c1;
    }

    public JButton getB4() {
        return b4;
    }

    public JCheckBox getCb() {
        return cb;
    }

    public BillService getBillService() {
        return billService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }
}
