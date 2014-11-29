package orz.wizard.mao.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.GroupDao;
import orz.wizard.mao.forum.entity.Group;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;
    
    public Group getGroup(long groupId) {
        return groupDao.getGroupById(groupId);
    }
    
    public void insertGroup(Group group){
        groupDao.insertGroup(group);
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
}
