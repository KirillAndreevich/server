package hiring.daointerface;

import hiring.database.DataBaseException;

import java.util.UUID;

public interface UserDao {
    void  deleteUser(UUID token) throws DataBaseException, DaoException;

    //boolean UserIsOnline(UUID token);

    void loginUser(String login, String password) throws DataBaseException;

    void exitUser(UUID token) throws DataBaseException, DaoException;

    void changeEmail(UUID token, String email) throws DataBaseException, DaoException;

    void changePassword(UUID token, String password) throws DataBaseException, DaoException;

}
