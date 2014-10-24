package orz.wizard.mao.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.UserDao;
import orz.wizard.mao.forum.entity.User;

@Service
public class UserService {
    
    @Autowired
    UserDao userDao;
    
    public User getUser(long id) {
        return userDao.getUserById(id);
    }
}
