package presentation.view;

import presentation.controller.TransferController;

import model.Utility;
import service.*;
import utils.DataConverter;

import javax.swing.*;
import java.awt.*;

public class TransferView extends JFrame {
    private static final long serialVersionUID=1L;
    private JPanel panel=new JPanel( new GridBagLayout());
    GridBagConstraints c=new GridBagConstraints();
    private TransferController controller=new TransferController(this);
    private DataConverter dataConverter;
    private AccountService accountService;
    private ClientService clientService;
    private UtilityService utilityService;
    private TransferService transferService;
    private BillService billService;
    private JTable table1=new JTable();
    private JTable table2=new JTable();
    private JLabel l1=new JLabel("Source");
    private JLabel l2=new JLabel("Destination");
    private JLabel l3=new JLabel("Amount");
    private JLabel l4=new JLabel("Bill");
    private JLabel l5=new JLabel("Utility");
    private JTextField t1=new JTextField(5);
    private JCheckBox cb=new JCheckBox();
    private JComboBox c1=new JComboBox();
    private JButton b1=new JButton("Transfer");
    private JButton b2=new JButton("Refresh");

    public TransferView(String name, ClientService clientService, AccountService accountService, UtilityService utilityService, TransferService transferService, BillService billService, DataConverter dataConverter){
        super(name);
        this.clientService=clientService;
        this.accountService=accountService;
        this.utilityService=utilityService;
        this.billService=billService;
        this.transferService=transferService;
        c.gridy=0;
        c.gridy=0;
        panel.add(l1,c);
        c.gridy=1;
        c.gridy=0;
        panel.add(l2,c);
        c.gridy=2;
        c.gridy=0;
        panel.add(l3,c);
        c.gridy=3;
        c.gridy=0;
        panel.add(l4,c);
        c.gridy=4;
        c.gridy=0;
        panel.add(l5,c);
        c.gridx=2;
        c.gridy=1;
        panel.add(t1,c);
        c.gridx=3;
        c.gridy=1;
        panel.add(cb,c);
        c.gridx=4;
        c.gridy=1;
        this.addItems();
        panel.add(c1,c);
        c.gridx=5;
        c.gridy=1;
        panel.add(b1,c);
        b1.addActionListener(controller);
        c.gridx=0;
        c.gridy=2;
        JScrollPane pane=new JScrollPane(table1);
        panel.add(pane,c);
        c.gridx=1;
        c.gridy=2;
        JScrollPane pane1=new JScrollPane(table2);
        panel.add(pane1,c);
        c.gridx=0;
        c.gridy=1;
        panel.add(b2,c);
        b2.addActionListener(controller);
        this.dataConverter=dataConverter;
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void addItems() {
        for(Utility u:utilityService.findAll()){
            c1.addItem(u.getName());
        }
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

    public JCheckBox getCb() {
        return cb;
    }

    public JComboBox getC1() {
        return c1;
    }

    public JButton getB1() {
        return b1;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public BillService getBillService() {
        return billService;
    }

    public JButton getB2() {
        return b2;
    }

    public DataConverter getDataConverter() {
        return dataConverter;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }
}
