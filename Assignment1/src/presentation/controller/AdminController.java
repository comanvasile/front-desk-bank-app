package presentation.controller;

import model.Bill;
import model.Transfer;
import model.User;
import presentation.view.AdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdminController implements ActionListener {
    private AdminView view;

    public AdminController(AdminView view){
        this.view=view;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        view.getTable().setVisible(false);
        view.getTable2().setVisible(false);
        view.getTable3().setVisible(false);
        if(source==view.getB1()) {

            List<User> users = view.getUserService().findAll();

            Object[][] data = view.getDataConverter().userToTableData(users);
            String[] columns = view.getDataConverter().userToTableColumnNames();

            refreshTable(data, columns,view.getTable());
            view.getTable().setVisible(true);
        }
        else if(source==view.getB2()) {
            if(view.getT1().getText().isEmpty()||view.getT2().getText().isEmpty()||view.getT3().getText().isEmpty()||view.getT4().getText().isEmpty()){
                JOptionPane.showMessageDialog(view,"Please complete all the fields");
            }
            else if(view.getT3().getText().equals(view.getUserService().findByUsername(view.getT3().getText()).getUsername()))
            {
                JOptionPane.showMessageDialog(view,"This username is already used");
            }
            else
            {
                User u = new User();
                u.setFistName(view.getT1().getText());
                u.setLastName(view.getT2().getText());
                u.setUsername(view.getT3().getText());
                u.setPassword(view.getT4().getText());
                u.setAdmin(((boolean) view.getC1().getSelectedItem()));
                view.getUserService().save(u);
                view.getT1().setText(null);
                view.getT2().setText(null);
                view.getT3().setText(null);
                view.getT4().setText(null);
            }
        }
        else if(source==view.getB3()){


            int index=view.getTable().getSelectedRow();
            int i=0;
            for(User u:view.getUserService().findAll()){
                if(i==index){
                    if(!view.getT1().getText().isEmpty())
                        u.setFistName(view.getT1().getText());
                    if(!view.getT1().getText().isEmpty())
                        u.setLastName(view.getT2().getText());
                    if(!view.getT1().getText().isEmpty())
                        u.setUsername(view.getT3().getText());
                    if(!view.getT1().getText().isEmpty())
                        u.setPassword(view.getT4().getText());
                    if(view.getT3().getText().equals(view.getUserService().findByUsername(view.getT3().getText()).getUsername()) && u.getId()!=view.getUserService().findByUsername(view.getT3().getText()).getId()){
                        JOptionPane.showMessageDialog(view,"This username is already used");

                    }
                    else {
                        view.getUserService().save(u);
                    }
                }
                i++;
            }
            view.getT1().setText(null);
            view.getT2().setText(null);
            view.getT3().setText(null);
            view.getT4().setText(null);

        }
        else if(source==view.getB4()){
            int index=view.getTable().getSelectedRow();
            int i=0;
            for(User user:view.getUserService().findAll()){
                if(i==index){
                    view.getUserService().delete(user.getId());


                }
                i++;
            }

        }
        else if(source==view.getB5()){
            User user=new User();
            int index=view.getTable().getSelectedRow();
            int i=0;
            for(User u:view.getUserService().findAll()){
                if(i==index){
                    user=u;
                }
                i++;
            }
            if(view.getT5().getText().isEmpty() ||view.getT6().getText().isEmpty()|| user.isAdmin()){
                JOptionPane.showMessageDialog(view,"Please complete end field and start field and select an employee");

            }
            else
                {
                    String startDate=view.getT5().getText();
                    String endDate=view.getT6().getText();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = null;
                    java.util.Date date2=null;
                    try {
                        date = sdf1.parse(startDate);
                        date2=sdf1.parse(endDate);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                    java.sql.Date sqlEndDate = new java.sql.Date(date2.getTime());

                        List<Transfer> transfers = view.getTransferService().findByDateAndUser(sqlStartDate, sqlEndDate, user);

                        Object[][] data = view.getDataConverter().transferToTableData(transfers);
                        String[] columns = view.getDataConverter().transferToTableColumnNames();

                        refreshTable(data, columns, view.getTable2());
                        view.getTable2().setVisible(true);


                        List<Bill> bills = view.getBillService().findByDateAndUser(sqlStartDate, sqlEndDate, user);
                        Object[][] data2 = view.getDataConverter().billToTableData(bills);
                        String[] columns2 = view.getDataConverter().billToTableColumnNames();

                        refreshTable(data2, columns2, view.getTable());
                        view.getTable().setVisible(true);


            }
        }
    }
    private void refreshTable(Object[][] data, String[] columnNames,JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();

    }

}
