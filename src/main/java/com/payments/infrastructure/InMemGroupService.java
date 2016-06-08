package com.payments.infrastructure;

import com.payments.domain.group.Group;
import com.payments.domain.group.GroupId;
import com.payments.domain.group.GroupService;
import com.payments.domain.user.UserId;

import java.util.*;

/**
 * @author mciolek
 */
public class InMemGroupService implements GroupService {
    private final Map<GroupId, Group> groups = new HashMap<GroupId, Group>();

    public InMemGroupService() {
        groups.put(new GroupId("0"), new Group(null, "Root Group", new GroupId("0")));
    }

    public GroupId create(String name, GroupId parentId) {
        if (!groups.containsKey(parentId)) {
            throw new IllegalStateException(String.format("Parent with %s not exists.", parentId));
        }

        GroupId groupId = new GroupId(UUID.randomUUID().toString());
        Group group = new Group(parentId, name, groupId);
        groups.put(groupId, group);

        return groupId;
    }

    public Optional<Group> get(GroupId groupId) {
        return Optional.ofNullable(groups.get(groupId));
    }


    public void addUserToGroup(UserId userId, GroupId groupId) {

    }

    public List<GroupId> getUserGroups(UserId userId) {
        return null;
    }

    public List<UserId> getUsers(GroupId groupId) {
        return null;
    }
}
