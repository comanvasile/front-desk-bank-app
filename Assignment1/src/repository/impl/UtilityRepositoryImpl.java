package repository.impl;

import model.Utility;
import repository.UtilityRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilityRepositoryImpl implements UtilityRepository {
    private final JDBConnectionWrapper jdbConnectionWrapper;

    public UtilityRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }
    @Override
    public List<Utility> findAll() {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Utility> utilities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utility");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Utility u=new Utility();

                u.setId(resultSet.getLong(1));
                u.setName(resultSet.getString(2));
                u.setAmount(resultSet.getDouble(3));


                utilities.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilities;
    }

    @Override
    public Utility create(Utility utility) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utility (name,amount) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utility.getName());
            preparedStatement.setDouble(2, utility.getAmount());


            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                utility.setId(resultSet.getLong(1));
                return utility;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utility update(Utility utility) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE utility SET name=?, amount=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utility.getName());
            preparedStatement.setDouble(2, utility.getAmount());


            preparedStatement.setLong(3, utility.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return utility;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utility findUtilityByName(String name) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utility WHERE name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Utility item = new Utility();

                item.setId(resultSet.getLong(1));
                item.setName(resultSet.getString(2));
                item.setAmount(resultSet.getDouble(3));

                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
