package com.payments.domain.group;

import com.payments.domain.user.UserId;

import java.util.List;
import java.util.Optional;

/**
 * @author mciolek
 */
public interface GroupService {

    GroupId create(String name, GroupId parentId);

    Optional<Group> get(GroupId groupId);

    void addUserToGroup(UserId userId, GroupId groupId);

    List<GroupId> getUserGroups(UserId userId);

    List<UserId> getUsers(GroupId groupId);

}
