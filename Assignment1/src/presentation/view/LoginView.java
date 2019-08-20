package presentation.view;

import presentation.controller.LoginController;
import service.*;
import utils.DataConverter;


import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private static final long serialVersionUID=1L;
    private JPanel panel=new JPanel( new GridBagLayout());
    GridBagConstraints c=new GridBagConstraints();
    private JLabel l1=new JLabel("Username");
    private JTextField t1=new JTextField(20);
    private JLabel l2=new JLabel("Password");
    private JPasswordField t2=new JPasswordField(20);
    private JButton b1=new JButton("Login");
    private LoginController controller=new LoginController(this);
    private UserService userService;
    private ClientService clientService;
    private AccountService accountService;
    private UtilityService utilityService;
    private BillService billService;
    private TransferService transferService;
    private DataConverter dataConverter;

    public LoginView(String name, UserService userService, ClientService clientService, AccountService accountService, UtilityService utilityService, TransferService transferService,BillService billService, DataConverter dataConverter){
        super(name);
        this.clientService=clientService;
        this.accountService=accountService;
        this.billService=billService;
        this.utilityService=utilityService;
        this.transferService=transferService;
        c.gridx=0;
        c.gridy=0;
        panel.add(l1,c);
        c.gridx=0;
        c.gridy=1;
        panel.add(t1,c);
        t1.addActionListener(controller);
        c.gridx=0;
        c.gridy=2;
        panel.add(l2,c);
        c.gridx=0;
        c.gridy=3;
        panel.add(t2,c);
        t2.addActionListener(controller);
        c.gridx=0;
        c.gridy=4;
        panel.add(b1,c);
        b1.addActionListener(controller);
        this.userService=userService;
        this.dataConverter=dataConverter;
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    public UserService getUserService() {
        return userService;
    }

    public JTextField getT1() {
        return t1;
    }

    public JTextField getT2() {
        return t2;
    }

    public JButton getB1() {
        return b1;
    }

    public DataConverter getDataConverter() {
        return dataConverter;
    }
    public AccountService getAccountService() {
        return accountService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public BillService getBillService() {
        return billService;
    }

    public TransferService getTransferService() {
        return transferService;
    }
}
