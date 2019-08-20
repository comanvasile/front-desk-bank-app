package repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnectionWrapper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final int TIMEOUT = 5;

    private Connection connection;

    public JDBConnectionWrapper(String schemaName) {
        try {
            connection = DriverManager.getConnection(DB_URL + schemaName + "?useSSL=false", USER, PASS);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  firstName varchar(255) NOT NULL," +
                "  lastName varchar(255) NOT NULL," +
                "  username varchar(255) NOT NULL," +
                "  password varchar(255) NOT NULL," +
                "  admin BIT NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS client (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  name varchar(255) NOT NULL," +
                "  identityCardNumber int NOT NULL," +
                "  address varchar(255) NOT NULL," +
                "  cnp varchar(255) NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS account (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  id_client BIGINT(100) NOT NULL," +
                "  identificationNumber varchar(255) NOT NULL," +
                "  type varchar(255) NOT NULL," +
                "  amount double NOT NULL," +
                "  creationDate datetime NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS utility (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  name varchar(255) NOT NULL," +
                "  amount double NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);
        sql = "CREATE TABLE IF NOT EXISTS transfer (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  id_sursa BIGINT(100) NOT NULL," +
                "  id_destinatie BIGINT(100) NOT NULL," +
                "  id_user BIGINT(100) NOT NULL," +
                "  amount double NOT NULL," +
                "  date datetime NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);
        sql = "CREATE TABLE IF NOT EXISTS bill (" +
                "  id BIGINT(100) NOT NULL AUTO_INCREMENT," +
                "  id_account BIGINT(100) NOT NULL," +
                "  id_utility BIGINT(100) NOT NULL," +
                "  id_user BIGINT(100) NOT NULL," +
                "  amount double NOT NULL," +
                "  date datetime NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);



    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection() {
        return connection;
    }
}
