package presentation.controller;

import model.Account;
import model.Client;
import presentation.view.TransferView;
import presentation.view.UserView;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Random;

public class UserController implements ActionListener {
    private UserView view;

    public UserController(UserView view){
        this.view=view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        view.getTable1().setVisible(false);
        view.getTable2().setVisible(false);
        if(source==view.getB1()){
            if(!view.getCb().isSelected()) {
                if(view.getT1().getText().isEmpty()||view.getT2().getText().isEmpty()||view.getT3().getText().isEmpty()||view.getT4().getText().isEmpty()){
                    JOptionPane.showMessageDialog(view,"Please complete all the fields");
                }
                else {
                    Client c = new Client();
                    c.setName(view.getT1().getText());
                    c.setIdentityCardNumber(new Integer(view.getT2().getText()));
                    c.setAddress(view.getT3().getText());
                    c.setCnp(view.getT4().getText());
                    view.getClientService().save(c);
                    view.getT1().setText(null);
                    view.getT2().setText(null);
                    view.getT3().setText(null);
                    view.getT4().setText(null);
                }
            }
            else {
                if (view.getT5().getText().isEmpty() || new Double(view.getT5().getText()) < 0) {
                    JOptionPane.showMessageDialog(view, "Amount must be 0 or greater and not null");
                } else {
                    Account a = new Account();
                    a.setIdentificationNumber(generateIban());
                    a.setCreationDate(new Date(System.currentTimeMillis()));
                    a.setAmount(new Double(view.getT5().getText()));
                    a.setType(view.getC1().getSelectedItem().toString());
                    int index = view.getTable1().getSelectedRow();
                    int i = 0;
                    for (Client u : view.getClientService().findAll()) {
                        if (i == index) {
                            a.setClient(u);
                            view.getAccountService().save(a);

                        }
                        i++;
                    }
                    view.getT5().setText(null);
                }
            }
        }
        else if(source==view.getB2()){
            if(!view.getCb().isSelected()) {
                int index = view.getTable1().getSelectedRow();
                int i = 0;
                for (Client u : view.getClientService().findAll()) {
                    if (i == index) {
                        if (!view.getT1().getText().isEmpty())
                            u.setName(view.getT1().getText());
                        if (!view.getT2().getText().isEmpty())
                            u.setIdentityCardNumber(new Integer(view.getT2().getText()));
                        if (!view.getT3().getText().isEmpty())
                            u.setAddress(view.getT3().getText());
                        if (!view.getT4().getText().isEmpty())
                            u.setCnp(view.getT4().getText());
                        view.getClientService().save(u);

                    }
                    i++;
                }
                view.getT1().setText(null);
                view.getT2().setText(null);
                view.getT3().setText(null);
                view.getT4().setText(null);
            }
            else{
                int index = view.getTable2().getSelectedRow();
                int i = 0;
                for(Account a: view.getAccountService().findAll()){
                    if(index==i){
                        if(!view.getT5().getText().isEmpty()){
                            a.setAmount(new Double(view.getT5().getText()));
                        }
                        a.setType(view.getC1().getSelectedItem().toString());
                        view.getAccountService().save(a);

                    }
                    i++;
                }
                view.getT5().setText(null);
            }

        }
        else if(source==view.getB3()){

                List<Client> clients = view.getClientService().findAll();

                Object[][] data = view.getDataConverter().clientToTableData(clients);
                String[] columns = view.getDataConverter().clientToTableColumnNames();

                refreshTable(data, columns,view.getTable1());
                view.getTable1().setVisible(true);

                List<Account> accounts = view.getAccountService().findAll();

                Object[][] data2 = view.getDataConverter().accountToTableData(accounts);
                String[] columns2 = view.getDataConverter().accountToTableColumnNames();

                refreshTable(data2, columns2,view.getTable2());
                view.getTable2().setVisible(true);

        }
        else if(source==view.getB4()){
            int index = view.getTable2().getSelectedRow();
            int i = 0;
            for(Account a: view.getAccountService().findAll()){
                if(i==index){
                    view.getAccountService().delete(a.getId());

                }
                i++;
            }

        }
        else if(source==view.getB5()){
            JFrame frame=new TransferView("Transfer/Pay",view.getClientService(),view.getAccountService(),view.getUtilityService(),view.getTransferService(),view.getBillService(),view.getDataConverter());
        }
    }
    private void refreshTable(Object[][] data, String[] columnNames, JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();

    }
    private String generateIban(){
        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        Random r = new Random();
        String iban=new String("RO");
        for (int i = 0; i < 22; i++) {
            iban=iban+alphabet.charAt(r.nextInt(N));
        }
        return iban;
    }
}
