package service.impl;

import model.Bill;
import model.User;
import repository.BillRepository;
import service.BillService;

import java.sql.Date;
import java.util.List;

public class BillServiceImpl implements BillService {
    private BillRepository billRepository;
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository=billRepository;
    }

    @Override
    public Bill save(Bill bill) {
        if (bill.getId() != null) {
            return billRepository.update(bill);
        } else {
            return billRepository.create(bill);
        }
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> findByDateAndUser(Date start, Date end, User u) {
        return billRepository.findByDateAndName(start,end,u);
    }
}
