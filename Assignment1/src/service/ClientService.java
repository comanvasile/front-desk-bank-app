package service;

import model.Client;

import java.util.List;

public interface ClientService {

    Client save(Client client);

    List<Client> findAll();
}
