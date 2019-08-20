package service.impl;

import model.Transfer;
import model.User;
import repository.TransferRepository;
import service.TransferService;

import java.sql.Date;
import java.util.List;

public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;
    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository=transferRepository;
    }

    @Override
    public Transfer save(Transfer transfer) {
        if (transfer.getId() != null) {
            return transferRepository.update(transfer);
        } else {
            return transferRepository.create(transfer);
        }

    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public List<Transfer> findByDateAndUser(Date start, Date end, User user) {
        return transferRepository.findByDateAndUser(start,end,user);
    }


}
