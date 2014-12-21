package orz.wizard.mao.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.UserDao;
import orz.wizard.mao.forum.entity.Group;
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
    
    public void insertUser(User user){
    	userDao.insertUser(user);
    }

    public UserInfo getUserInfo(long id) {
        return userDao.getUserInfoById(id);
    }

    public void updateUserInfo(UserInfo userInfo) {
        userDao.updateUserInfo(userInfo);
    }
    
    public List<Group> getGroupList(long userId) {
        return userDao.getGroupListByUserId(userId);
    }

    public List<User> getFollowerList(long userId) {
        return userDao.getFollowerList(userId);
    }
    
    public List<User> getFolloweeList(long userId) {
        return userDao.getFolloweeList(userId);
    }
    
    public boolean isFollowed(long fromUserId, long toUserId) {
        return userDao.findFollow(fromUserId, toUserId) != 0;
    }

    public void doFollow(long fromUserId, long toUserId) {
        userDao.doFollow(fromUserId, toUserId);
    }
    
    public void cancelFollow(long fromUserId, long toUserId) {
        userDao.cancelFollow(fromUserId, toUserId);
    }

    public void updateAvatar(long userId) {
        userDao.updateAvatar(userId);
    }

    public void insertCode(long userId, String code) {
        userDao.insertCode(userId, code);
    }
}
