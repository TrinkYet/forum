package orz.wizard.mao.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.GroupDao;
import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.User;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;
    
    public Group getGroup(long groupId) {
        return groupDao.getGroupById(groupId);
    }
    
    public void insertGroup(Group group){
        groupDao.insertGroup(group);
        joinGroup(group.getGroupId(), group.getUserId());
    }
    
    public boolean isJoined(long groupId, long userId) {
        return groupDao.findMembership(groupId, userId) != 0;
    }
    
    public void joinGroup(long groupId, long userId) {
        groupDao.joinGroup(groupId, userId);
    }
    
    public void quitGroup(long groupId, long userId) {
        groupDao.quitGroup(groupId, userId);
    }

    public List<User> getRecentUserList(long groupId) {
        return groupDao.getRecentUserList(groupId);
    }

    public int getUserCount(long groupId) {
        return groupDao.getUserCount(groupId);
    }

    public List<User> getUserList(long groupId) {
        return groupDao.getUserListById(groupId);
    }

    public List<Group> searchGroup(String q) {
        return groupDao.searchGroup(q);
    }

    public void saveGroup(Group group) {
        groupDao.saveGroup(group);
    }

    public void delete(long groupId) {
        groupDao.delete(groupId);
    }
}
