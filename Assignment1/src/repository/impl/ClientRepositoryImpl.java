package repository.impl;

import model.Client;
import model.User;
import repository.ClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
    private final JDBConnectionWrapper jdbConnectionWrapper;

    public ClientRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public List<Client> findAll() {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Client> clients = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Client client = new Client();


                client.setId(resultSet.getLong(1));
                client.setName(resultSet.getString(2));
                client.setIdentityCardNumber(resultSet.getInt(3));
                client.setAddress(resultSet.getString(4));
                client.setCnp(resultSet.getString(5));


                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client findById(Long id) {

        return null;
    }

    @Override
    public Client create(Client client) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client (name,identityCardNumber,address, cnp) VALUES( ?, ?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, client.getIdentityCardNumber());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4,client.getCnp());


            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                client.setId(resultSet.getLong(1));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client update(Client client) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE client SET name=?, identityCardNumber=?, address=?, cnp=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, client.getIdentityCardNumber());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4,client.getCnp());
            preparedStatement.setLong(5, client.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return client;
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
        return false;
    }
}
