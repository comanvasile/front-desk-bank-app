package repository.impl;

import model.Account;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements repository.AccountRepository {
    private final JDBConnectionWrapper jdbConnectionWrapper;

    public AccountRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public List<Account> findAll() {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account as a INNER JOIN client as c on a.id_client=c.id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account();
                Client client=new Client();

                client.setId(resultSet.getLong(2));
                client.setName(resultSet.getString(8));
                client.setIdentityCardNumber(resultSet.getInt(9));
                client.setAddress(resultSet.getString(10));
                client.setCnp(resultSet.getString(11));


                account.setId(resultSet.getLong(1));
                account.setClient(client);
                account.setIdentificationNumber(resultSet.getString(3));
                account.setType(resultSet.getString(4));
                account.setAmount(resultSet.getDouble(5));
                account.setCreationDate(resultSet.getDate(6));


                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public Account create(Account account) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO account (id_client,identificationNumber,type,amount,creationDate) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,account.getClient().getId());
            preparedStatement.setString(2,account.getIdentificationNumber());
            preparedStatement.setString(3,account.getType());
            preparedStatement.setDouble(4,account.getAmount());
            preparedStatement.setDate(5,account.getCreationDate());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                account.setId(resultSet.getLong(1));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account update(Account account) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account SET id_client=?, identificationNumber=?, type=?,amount=?,creationDate=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, account.getClient().getId());
            preparedStatement.setString(2, account.getIdentificationNumber());
            preparedStatement.setString(3, account.getType());
            preparedStatement.setDouble(4, account.getAmount());
            preparedStatement.setDate(5, account.getCreationDate());

            preparedStatement.setLong(6, account.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return account;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {

        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE id= ?");
            preparedStatement.setLong(1, id);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
