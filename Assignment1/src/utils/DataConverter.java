package utils;

import model.*;

import java.util.List;

public interface DataConverter {
    Object[][] userToTableData(List<User> users);

    String[] userToTableColumnNames();

    Object[][] clientToTableData(List<Client> clients);

    String[] clientToTableColumnNames();

    Object[][] accountToTableData(List<Account> accounts);

    String[] accountToTableColumnNames();

    Object[][] accountToTableData2(List<Account> accounts);

    String[] accountToTableColumnNames2();

    Object[][] transferToTableData(List<Transfer> transfers);

    String[] transferToTableColumnNames();
    Object[][] billToTableData(List<Bill> bills);

    String[] billToTableColumnNames();
}
