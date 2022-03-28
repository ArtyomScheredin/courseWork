package spbstu.deans_office.services;

import spbstu.deans_office.models.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllLimitBy(Integer limit);
}
