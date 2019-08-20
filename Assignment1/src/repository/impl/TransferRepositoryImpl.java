package repository.impl;

import model.Account;
import model.Client;
import model.Transfer;
import model.User;
import repository.TransferRepository;
import utils.impl.ContextHolderImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferRepositoryImpl implements TransferRepository {
    private JDBConnectionWrapper jdbConnectionWrapper;
    public TransferRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
       this.jdbConnectionWrapper=jdbConnectionWrapper;
    }

    @Override
    public List<Transfer> findAll() {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Transfer> transfers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank.client as c inner join bank.account as a on c.id=a.id_client inner join bank.transfer as t on a.id=t.id_sursa inner join bank.account as ac on t.id_destinatie=ac.id inner join bank.client as cl on ac.id_client=cl.id inner join bank.user as u on u.id=t.id_user ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account sursa=new Account();
                Client clientSursa=new Client();
                Account destinatie=new Account();
                Client clientDestinatie=new Client();
                Transfer transfer=new Transfer();
                User user=new User();

                clientSursa.setId(resultSet.getLong(1));
                clientSursa.setName(resultSet.getString(2));
                clientSursa.setIdentityCardNumber(resultSet.getInt(3));
                clientSursa.setAddress(resultSet.getString(4));
                clientSursa.setCnp(resultSet.getString(5));

                sursa.setId(resultSet.getLong(6));
                sursa.setClient(clientSursa);
                sursa.setIdentificationNumber(resultSet.getString(8));
                sursa.setType(resultSet.getString(9));
                sursa.setAmount(resultSet.getDouble(10));
                sursa.setCreationDate(resultSet.getDate(11));

                clientDestinatie.setId(resultSet.getLong(24));
                clientDestinatie.setName(resultSet.getString(25));
                clientDestinatie.setIdentityCardNumber(resultSet.getInt(26));
                clientDestinatie.setAddress(resultSet.getString(27));
                clientDestinatie.setCnp(resultSet.getString(28));

                destinatie.setId(resultSet.getLong(18));
                destinatie.setClient(clientDestinatie);
                destinatie.setIdentificationNumber(resultSet.getString(20));
                destinatie.setType(resultSet.getString(21));
                destinatie.setAmount(resultSet.getDouble(22));
                destinatie.setCreationDate(resultSet.getDate(23));

                user.setId(resultSet.getLong(29));
                user.setFistName(resultSet.getString(30));
                user.setLastName(resultSet.getString(31));
                user.setUsername(resultSet.getString(32));
                user.setPassword(resultSet.getString(33));
                user.setAdmin(resultSet.getBoolean(34));

                transfer.setId(resultSet.getLong(12));
                transfer.setSource(sursa);
                transfer.setDestination(destinatie);
                transfer.setUser(user);
                transfer.setAmount(resultSet.getDouble(16));
                transfer.setDate(resultSet.getDate(17));


                transfers.add(transfer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transfers;
    }

    @Override
    public List<Transfer> findByDateAndUser(Date start, Date end, User user) {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Transfer> transfers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank.client as c inner join bank.account as a on c.id=a.id_client inner join bank.transfer as t on a.id=t.id_sursa inner join bank.account as ac on t.id_destinatie=ac.id inner join bank.client as cl on ac.id_client=cl.id inner join bank.user as u on u.id=t.id_user where u.id=? and t.date>=? and t.date<=?");
            preparedStatement.setLong(1,user.getId());
            preparedStatement.setDate(2,start);
            preparedStatement.setDate(3,end);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account sursa=new Account();
                Client clientSursa=new Client();
                Account destinatie=new Account();
                Client clientDestinatie=new Client();
                Transfer transfer=new Transfer();
                User user2=new User();

                clientSursa.setId(resultSet.getLong(1));
                clientSursa.setName(resultSet.getString(2));
                clientSursa.setIdentityCardNumber(resultSet.getInt(3));
                clientSursa.setAddress(resultSet.getString(4));
                clientSursa.setCnp(resultSet.getString(5));

                sursa.setId(resultSet.getLong(6));
                sursa.setClient(clientSursa);
                sursa.setIdentificationNumber(resultSet.getString(8));
                sursa.setType(resultSet.getString(9));
                sursa.setAmount(resultSet.getDouble(10));
                sursa.setCreationDate(resultSet.getDate(11));

                clientDestinatie.setId(resultSet.getLong(24));
                clientDestinatie.setName(resultSet.getString(25));
                clientDestinatie.setIdentityCardNumber(resultSet.getInt(26));
                clientDestinatie.setAddress(resultSet.getString(27));
                clientDestinatie.setCnp(resultSet.getString(28));

                destinatie.setId(resultSet.getLong(18));
                destinatie.setClient(clientDestinatie);
                destinatie.setIdentificationNumber(resultSet.getString(20));
                destinatie.setType(resultSet.getString(21));
                destinatie.setAmount(resultSet.getDouble(22));
                destinatie.setCreationDate(resultSet.getDate(23));

                user2.setId(resultSet.getLong(29));
                user2.setFistName(resultSet.getString(30));
                user2.setLastName(resultSet.getString(31));
                user2.setUsername(resultSet.getString(32));
                user2.setPassword(resultSet.getString(33));
                user2.setAdmin(resultSet.getBoolean(34));

                transfer.setId(resultSet.getLong(12));
                transfer.setSource(sursa);
                transfer.setDestination(destinatie);
                transfer.setUser(user2);
                transfer.setAmount(resultSet.getDouble(16));
                transfer.setDate(resultSet.getDate(17));


                transfers.add(transfer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transfers;
    }

    @Override
    public Transfer create(Transfer transfer) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO transfer (id_sursa,id_destinatie,id_user,amount,date) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,transfer.getSource().getId());
            preparedStatement.setLong(2,transfer.getDestination().getId());
            preparedStatement.setLong(3,transfer.getUser().getId());
            preparedStatement.setDouble(4,transfer.getAmount());
            preparedStatement.setDate(5,transfer.getDate());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                transfer.setId(resultSet.getLong(1));
                return transfer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transfer update(Transfer transfer) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE transfer SET id_sursa=?, id_destinatie=?, id_user=?,amount=?,date=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, transfer.getSource().getId());
            preparedStatement.setLong(2, transfer.getDestination().getId());
            preparedStatement.setLong(3, transfer.getUser().getId());
            preparedStatement.setDouble(4, transfer.getAmount());
            preparedStatement.setDate(5, transfer.getDate());

            preparedStatement.setLong(6, transfer.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return transfer;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
