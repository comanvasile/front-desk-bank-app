package repository;

import model.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();

    Client findById(Long id);

    Client create(Client client);

    Client update(Client client);

    boolean deleteById(Long id);
}
