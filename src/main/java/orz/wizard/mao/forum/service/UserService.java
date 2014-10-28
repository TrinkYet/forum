package orz.wizard.mao.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.UserDao;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.entity.UserInfo;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    public User getUser(long id) {
        return userDao.getUserById(id);
    }
    
    public User getUser(String email) {
        return userDao.getUserByEmail(email);
    }
    
    public User saveUser(User user){
    	return userDao.saveUser(user);
    }

    public UserInfo getUserInfo(long id) {
        return userDao.getUserInfoById(id);
    }

    public void saveUserInfo(UserInfo userInfo, long id) {
        userDao.saveUserInfo(userInfo, id);
    }
}
