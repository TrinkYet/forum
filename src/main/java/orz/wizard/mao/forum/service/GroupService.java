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
    
    public Group getGroup(long id) {
        return groupDao.getGroupById(id);
    }
    
    public List<Group> getGroupList(long id) {
        return groupDao.getGroupListById(id);
    }
    
    public Group saveGroup(Group group, long userId){
        return groupDao.saveGroup(group, userId);
    }
    
    public boolean isJoined(long userId, long groupId) {
        return groupDao.getMembershipByTwoId(userId, groupId) != null;
    }
    
    public void joinGroup(long userId, long groupId) {
        groupDao.joinGroup(userId, groupId);
    }
}
