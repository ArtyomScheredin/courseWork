package spbstu.deans_office.services.service_impl;

import spbstu.deans_office.models.Group;
import spbstu.deans_office.repositories.GroupRepository;
import spbstu.deans_office.services.GroupService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllLimitBy(Integer limit) {
        return groupRepository.findAllLimitBy(limit);
    }
}
