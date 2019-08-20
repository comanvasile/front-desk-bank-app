package presentation.controller;

import model.Account;
import model.Bill;
import model.Transfer;
import model.Utility;
import presentation.view.TransferView;
import utils.impl.ContextHolderImpl;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class TransferController implements ActionListener {
    private TransferView view;

    public TransferController(TransferView view){
        this.view=view;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        view.getTable1().setVisible(false);
        view.getTable2().setVisible(false);
        if(source==view.getB1()){

            int index = view.getTable1().getSelectedRow();
            int i = 0;
            Account sursa=new Account();
            for(Account a:view.getAccountService().findAll()){
                if(index==i){
                    sursa=a;

                }
                i++;
            }
            Double amount=new Double(0);
            if(!view.getT1().getText().isEmpty())
             amount = new Double(view.getT1().getText());
            if(!view.getCb().isSelected()) {
                index = view.getTable2().getSelectedRow();
                i = 0;
                Account destination = new Account();
                for (Account a : view.getAccountService().findAll()) {
                    if (index == i) {
                        destination = a;

                    }
                    i++;
                }
                if (sursa.getAmount() < amount ) {
                    JOptionPane.showMessageDialog(view, "Fonduri insuficiente");
                }
                else if(sursa.getId()==destination.getId() || view.getT1().getText().isEmpty()||amount<0){
                    JOptionPane.showMessageDialog(view, "Operatiune imposibila");
                }
                else
                {
                    Transfer t=new Transfer();
                    t.setSource(sursa);
                    t.setDestination(destination);
                    t.setUser(ContextHolderImpl.getCurrentUser());
                    t.setAmount(amount);
                    t.setDate(new Date(System.currentTimeMillis()));
                    sursa.setAmount(sursa.getAmount()-amount);
                    destination.setAmount(destination.getAmount()+amount);
                    view.getAccountService().save(sursa);
                    view.getAccountService().save(destination);
                    view.getTransferService().save(t);
                    view.getT1().setText(null);
                }
            }
            else{
                if(sursa.getAmount()<amount){
                    JOptionPane.showMessageDialog(view, "Fonduri insuficiente");

                }
                else if(view.getT1().getText().isEmpty()){
                    JOptionPane.showMessageDialog(view, "Operatiune imposibila");

                }
                else{
                    Bill bill=new Bill();
                    Utility utility=view.getUtilityService().findUtilityByName(view.getC1().getSelectedItem().toString());
                    bill.setAccount(sursa);
                    bill.setAmount(amount);
                    bill.setDate(new Date(System.currentTimeMillis()));
                    bill.setUser(ContextHolderImpl.getCurrentUser());
                    bill.setUtility(utility);
                    sursa.setAmount(sursa.getAmount()-amount);
                    utility.setAmount(utility.getAmount()+amount);
                    view.getUtilityService().save(utility);
                    view.getAccountService().save(sursa);
                    view.getBillService().save(bill);
                    view.getT1().setText(null);
                }
            }



        }
        else if(source==view.getB2()){
            List<Account> accounts = view.getAccountService().findAll();

            Object[][] data = view.getDataConverter().accountToTableData2(accounts);
            String[] columns = view.getDataConverter().accountToTableColumnNames2();

            refreshTable(data, columns,view.getTable1());
            view.getTable1().setVisible(true);

            refreshTable(data, columns,view.getTable2());
            view.getTable2().setVisible(true);

        }
    }
    private void refreshTable(Object[][] data, String[] columnNames, JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();

    }
}
