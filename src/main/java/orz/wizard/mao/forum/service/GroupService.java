package orz.wizard.mao.forum.service;

import java.util.List;

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
    
    public List<Group> getGroupList(long id) {
        return groupDao.getGroupListById(id);
    }
}
