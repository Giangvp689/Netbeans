package Controller;

import Model.DAO.AccountDAO;
import Model.Entity.AccountEntity;
import java.security.MessageDigest;
import java.util.List;

public class AccountController {

    private AccountDAO dao = new AccountDAO();

    public List<AccountEntity> getAllAccounts() {
        return dao.getAll();
    }

    public boolean addAccount(AccountEntity acc) {
        return dao.insert(acc);
    }

    public boolean updateAccount(AccountEntity acc) {
        return dao.update(acc);
    }

    public boolean deleteAccount(int idTk) {
        return dao.delete(idTk);
    }

    public List<AccountEntity> searchAccounts(String keyword) {
        return dao.search(keyword);
    }

  
}
