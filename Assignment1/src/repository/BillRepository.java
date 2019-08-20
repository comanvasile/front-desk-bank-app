package repository;

import model.Bill;
import model.User;

import java.sql.Date;
import java.util.List;

public interface BillRepository {
    List<Bill> findAll();

    List<Bill>findByDateAndName(Date start, Date end, User u);

    Bill create(Bill bill);

    Bill update(Bill bill);
}
