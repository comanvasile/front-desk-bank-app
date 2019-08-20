package service.impl;

import model.Client;
import model.User;
import repository.ClientRepository;
import service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public Client save(Client client) {
        if (client.getId() != null) {
            return clientRepository.update(client);
        } else {
            return clientRepository.create(client);
        }
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
