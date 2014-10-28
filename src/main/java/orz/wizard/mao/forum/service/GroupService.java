package orz.wizard.mao.forum.service;

import org.springframework.beans.factory.annotation.Autowired;

import orz.wizard.mao.forum.dao.GroupDao;
import orz.wizard.mao.forum.entity.Group;

public class GroupService {
    @Autowired
    private GroupDao groupDao;
    
    public Group getGroup(long id) {
        return groupDao.getGroupById(id);
    }
}
