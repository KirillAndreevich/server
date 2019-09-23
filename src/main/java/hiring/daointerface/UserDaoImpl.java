package hiring.daointerface;

import hiring.database.DataBase;
import hiring.database.DataBaseException;

import java.util.UUID;

public class UserDaoImpl implements UserDao{
    public UserDaoImpl(){

    }
    public void deleteUser(UUID token) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.deleteUser(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    //public boolean UserIsOnline(UUID token);

    public void loginUser(String login, String password) throws DataBaseException {
        DataBase.loginUser(login, password);

    }

    public void exitUser(UUID token) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.exitUser(token);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);
    }

    public void changeEmail(UUID token, String email) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changeEmail(token, email);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);

    }

    public void changePassword(UUID token, String password) throws DataBaseException, DaoException {
        if (DataBase.UserIsOnline(token)) {
            DataBase.changePassword(token, password);
        } else throw new DaoException(DaoErrorCode.USER_OFFLINE);

    }
}
