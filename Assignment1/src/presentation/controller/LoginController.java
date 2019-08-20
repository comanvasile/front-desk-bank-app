package presentation.controller;

import model.User;
import presentation.view.AdminView;
import presentation.view.LoginView;
import presentation.view.UserView;
import utils.impl.ContextHolderImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginView view;
    public LoginController(LoginView view){
        this.view=view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            Object source=e.getSource();
            if(source==view.getB1()){
                User user=view.getUserService().login(view.getT1().getText(),view.getT2().getText());
                if(user!=null && user.isAdmin()){
                    @SuppressWarnings("unused")
                    JFrame frame = new AdminView("Admin",view.getUserService(),view.getTransferService(),view.getBillService(),view.getDataConverter());
                }
                else if(user!=null && !user.isAdmin()){
                    @SuppressWarnings("unused")
                    JFrame frame = new UserView("User",view.getClientService(),view.getAccountService(),view.getUtilityService(),view.getTransferService(),view.getBillService(),view.getDataConverter());
                    ContextHolderImpl.setCurrentUser(user);
                }
                else{
                    JOptionPane.showMessageDialog(view,"Credentialele nu sunt corecte!");

                }
            }

    }
}
