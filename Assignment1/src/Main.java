import presentation.view.LoginView;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;
import utils.DataConverter;
import utils.impl.DataConverterImpl;

import javax.swing.*;

public class Main {

    private static final String SCHEMA_NAME = "bank";


    public static void main(String[] args) {


        JDBConnectionWrapper jdbConnectionWrapper = new JDBConnectionWrapper(SCHEMA_NAME);

        //initialize repo
        UserRepository userRepository = new UserRepositoryImpl(jdbConnectionWrapper);
        AccountRepository accountRepository = new AccountRepositoryImpl(jdbConnectionWrapper);
        ClientRepository clientRepository = new ClientRepositoryImpl(jdbConnectionWrapper);
        BillRepository billRepository=new BillRepositoryImpl(jdbConnectionWrapper);
        TransferRepository transferRepository=new TransferRepositoryImpl(jdbConnectionWrapper);
        UtilityRepository utilityRepository=new UtilityRepositoryImpl(jdbConnectionWrapper);

        //initialize service
        UserService userService = new UserServiceImpl(userRepository);
        ClientService clientService=new ClientServiceImpl(clientRepository);
        AccountService accountService=new AccountServiceImpl(accountRepository);
        BillService billService=new BillServiceImpl(billRepository);
        TransferService transferService=new TransferServiceImpl(transferRepository);
        UtilityService utilityService=new UtilityServiceImpl(utilityRepository);

        DataConverter dataConverter = new DataConverterImpl();




        @SuppressWarnings("unused")
        JFrame frame = new LoginView("Bank",userService,clientService,accountService,utilityService,transferService,billService,dataConverter);
    }

}
