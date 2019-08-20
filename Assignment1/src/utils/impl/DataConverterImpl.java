package utils.impl;

import model.*;
import utils.DataConverter;

import java.util.List;

public class DataConverterImpl implements DataConverter {
    @Override
    public Object[][] userToTableData(List<User> users) {
        Object[][] data = new Object[users.size()][6];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = users.get(row).getId();
            data[row][1] = users.get(row).getFistName();
            data[row][2] = users.get(row).getLastName();
            data[row][3] = users.get(row).getUsername();
            data[row][4] = users.get(row).getPassword();
            data[row][5] = users.get(row).isAdmin();

        }
        return data;    }
    @Override
    public String[] userToTableColumnNames() {
        return new String[]{"Id", "First Name", "Last Name", "Username ","Password","Admin"};
    }

    @Override
    public Object[][] clientToTableData(List<Client> clients) {
        Object[][] data = new Object[clients.size()][5];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = clients.get(row).getId();
            data[row][1] = clients.get(row).getName();
            data[row][2] = clients.get(row).getIdentityCardNumber();
            data[row][3] = clients.get(row).getAddress();
            data[row][4] = clients.get(row).getCnp();


        }
        return data;
    }

    @Override
    public String[] clientToTableColumnNames() {
        return new String[]{"Id", "Name", "ID Number", "Address","CNP"};

    }

    @Override
    public Object[][] accountToTableData(List<Account> accounts) {
        Object[][] data = new Object[accounts.size()][6];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = accounts.get(row).getId();
            data[row][1] = accounts.get(row).getClient().getId();
            data[row][2] = accounts.get(row).getIdentificationNumber();
            data[row][3] = accounts.get(row).getType();
            data[row][4] = accounts.get(row).getAmount();
            data[row][5] = accounts.get(row).getCreationDate();


        }
        return data;
    }

    @Override
    public String[] accountToTableColumnNames() {
        return new String[]{"Id", "Id Client", "IBAN", "Type","Amount","Creation Date"};
    }

    @Override
    public Object[][] accountToTableData2(List<Account> accounts) {
        Object[][] data = new Object[accounts.size()][7];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = accounts.get(row).getId();
            data[row][1] = accounts.get(row).getClient().getName();
            data[row][2] = accounts.get(row).getClient().getId();
            data[row][3] = accounts.get(row).getIdentificationNumber();
            data[row][4] = accounts.get(row).getType();
            data[row][5] = accounts.get(row).getAmount();
            data[row][6] = accounts.get(row).getCreationDate();


        }
        return data;
    }

    @Override
    public String[] accountToTableColumnNames2() {
        return new String[]{"Id", "Client Name","Client Id", "IBAN", "Type","Amount","Creation Date"};    }

    @Override
    public Object[][] transferToTableData(List<Transfer> transfers) {
        Object[][] data = new Object[transfers.size()][7];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = transfers.get(row).getId();
            data[row][1] = transfers.get(row).getSource().getClient().getName();
            data[row][2] = transfers.get(row).getSource().getId();
            data[row][3] = transfers.get(row).getDestination().getClient().getName();
            data[row][4] = transfers.get(row).getDestination().getId();
            data[row][5] = transfers.get(row).getAmount();
            data[row][6] = transfers.get(row).getDate();


        }
        return data;
    }

    @Override
    public String[] transferToTableColumnNames() {
        return new String[]{"Id Transfer", "Expeditor","Id Cont", "Destinatar", "Id Cont","Amount"," Date"};
    }

    @Override
    public Object[][] billToTableData(List<Bill> bills) {
        Object[][] data = new Object[bills.size()][7];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = bills.get(row).getId();
            data[row][1] = bills.get(row).getAccount().getClient().getName();
            data[row][2] = bills.get(row).getAccount().getId();
            data[row][3] = bills.get(row).getUtility().getName();
            data[row][4] = bills.get(row).getUtility().getId();
            data[row][5] = bills.get(row).getAmount();
            data[row][6] = bills.get(row).getDate();


        }
        return data;
    }

    @Override
    public String[] billToTableColumnNames() {

        return new String[]{"Id Bill", "Nume Client","Id Cont", "Utility", "Id Utility","Amount"," Date"};

    }
}
