package orz.wizard.mao.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.GroupDao;
import orz.wizard.mao.forum.entity.Group;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;
    
    public Group getGroup(long id) {
        return groupDao.getGroupById(id);
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
