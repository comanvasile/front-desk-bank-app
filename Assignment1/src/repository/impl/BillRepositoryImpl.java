package repository.impl;

import model.*;
import repository.BillRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepositoryImpl implements BillRepository {
    JDBConnectionWrapper jdbConnectionWrapper;
    public BillRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper=jdbConnectionWrapper;
    }

    @Override
    public List<Bill> findAll() {

        Connection connection = jdbConnectionWrapper.getConnection();
        List<Bill> bills = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank.account as a inner join bank.bill as b on a.id=b.id_account inner join bank.utility as u on u.id=b.id_utility inner join bank.client as c on c.id=a.id_client inner join bank.user as us on us.id=b.id_user; ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Account sursa=new Account();
                Client clientSursa=new Client();

                clientSursa.setId(resultSet.getLong(16));
                clientSursa.setName(resultSet.getString(17));
                clientSursa.setIdentityCardNumber(resultSet.getInt(18));
                clientSursa.setAddress(resultSet.getString(19));
                clientSursa.setCnp(resultSet.getString(20));

                sursa.setId(resultSet.getLong(1));
                sursa.setClient(clientSursa);
                sursa.setIdentificationNumber(resultSet.getString(3));
                sursa.setType(resultSet.getString(4));
                sursa.setAmount(resultSet.getDouble(5));
                sursa.setCreationDate(resultSet.getDate(6));

                Utility utility=new Utility();

                utility.setId(resultSet.getLong(13));
                utility.setName(resultSet.getString(14));
                utility.setAmount(resultSet.getDouble(15));

                User user=new User();

                user.setId(resultSet.getLong(21));
                user.setFistName(resultSet.getString(22));
                user.setLastName(resultSet.getString(23));
                user.setUsername(resultSet.getString(24));
                user.setPassword(resultSet.getString(25));
                user.setAdmin(resultSet.getBoolean(26));

                Bill bill=new Bill();

                bill.setId(resultSet.getLong(7));
                bill.setUser(user);
                bill.setUtility(utility);
                bill.setAccount(sursa);
                bill.setAmount(resultSet.getDouble(11));
                bill.setDate(resultSet.getDate(12));



                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public List<Bill> findByDateAndName(Date start,Date end,User u) {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Bill> bills = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank.account as a inner join bank.bill as b on a.id=b.id_account inner join bank.utility as u on u.id=b.id_utility inner join bank.client as c on c.id=a.id_client inner join bank.user as us on us.id=b.id_user where us.id=? and b.date>=? and b.date<=? ");
            preparedStatement.setLong(1,u.getId());
            preparedStatement.setDate(2,start);
            preparedStatement.setDate(3,end);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Account sursa=new Account();
                Client clientSursa=new Client();

                clientSursa.setId(resultSet.getLong(16));
                clientSursa.setName(resultSet.getString(17));
                clientSursa.setIdentityCardNumber(resultSet.getInt(18));
                clientSursa.setAddress(resultSet.getString(19));
                clientSursa.setCnp(resultSet.getString(20));

                sursa.setId(resultSet.getLong(1));
                sursa.setClient(clientSursa);
                sursa.setIdentificationNumber(resultSet.getString(3));
                sursa.setType(resultSet.getString(4));
                sursa.setAmount(resultSet.getDouble(5));
                sursa.setCreationDate(resultSet.getDate(6));

                Utility utility=new Utility();

                utility.setId(resultSet.getLong(13));
                utility.setName(resultSet.getString(14));
                utility.setAmount(resultSet.getDouble(15));

                User user=new User();

                user.setId(resultSet.getLong(21));
                user.setFistName(resultSet.getString(22));
                user.setLastName(resultSet.getString(23));
                user.setUsername(resultSet.getString(24));
                user.setPassword(resultSet.getString(25));
                user.setAdmin(resultSet.getBoolean(26));

                Bill bill=new Bill();

                bill.setId(resultSet.getLong(7));
                bill.setUser(user);
                bill.setUtility(utility);
                bill.setAccount(sursa);
                bill.setAmount(resultSet.getDouble(11));
                bill.setDate(resultSet.getDate(12));



                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public Bill create(Bill bill) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bill (id_account,id_utility,id_user,amount,date) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,bill.getAccount().getId());
            preparedStatement.setLong(2,bill.getUtility().getId());
            preparedStatement.setLong(3,bill.getUser().getId());
            preparedStatement.setDouble(4,bill.getAmount());
            preparedStatement.setDate(5,bill.getDate());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                bill.setId(resultSet.getLong(1));
                return bill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bill update(Bill bill) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bill SET id_account=?, id_utility=?, id_user=?,amount=?,date=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, bill.getAccount().getId());
            preparedStatement.setLong(2, bill.getUtility().getId());
            preparedStatement.setLong(3, bill.getUser().getId());
            preparedStatement.setDouble(4, bill.getAmount());
            preparedStatement.setDate(5, bill.getDate());

            preparedStatement.setLong(6, bill.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return bill;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
