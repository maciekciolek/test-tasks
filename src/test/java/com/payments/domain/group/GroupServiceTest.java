package com.payments.domain.group;

import com.payments.domain.user.UserId;
import com.payments.infrastructure.InMemGroupService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

/**
 * @author mciolek
 */
public class GroupServiceTest {
    private GroupService service;
    private GroupId ROOT_GROUP = new GroupId("0");

    @Before
    public void before() {
        service = new InMemGroupService();
    }


    @Test(expected = IllegalStateException.class)
    public void createGroupShouldFailIfParentNotExists() {
        service.create("Test group", new GroupId("1"));
    }

    @Test
    public void createGroupShouldSucceedIfParentExists() {
        //given
        GroupId newGroupId = service.create("Test group", ROOT_GROUP);

        //when
        service.create("Test group", newGroupId);

    }

    @Test
    public void createGroupShouldSucceedStoreCreatedGroup() {
        //given
        GroupId newGroupId = service.create("Test group", ROOT_GROUP);

        //when
        Optional<Group> group = service.get(newGroupId);

        //then
        assertTrue(group.isPresent());
    }

    @Test(expected = IllegalStateException.class)
    public void addUserToGroupShouldFailIfGroupNotExists() {
        //given

        //when
        service.addUserToGroup(new UserId("tomasz"), new GroupId("10"));

        //then
    }

    @Test
    public void addUserToGroupShouldAddUserIfGroupExists() {
        //given
        GroupId groupId = service.create("Test group", ROOT_GROUP);

        //when
        service.addUserToGroup(new UserId("tomasz"), groupId);

        //then
    }

    @Test
    public void getUserGroupsShouldReturnAllUserGroups() {
        //given
        GroupId firstGroup = service.create("Test group 1", ROOT_GROUP);
        GroupId secondGroup = service.create("Test group 2", ROOT_GROUP);
        UserId userId = new UserId("tomasz");
        service.addUserToGroup(userId, firstGroup);
        service.addUserToGroup(userId, secondGroup);

        //when
        List<GroupId> result = service.getUserGroups(userId);

        //then
        assertTrue(result.size() == 2);
        assertTrue(result.contains(firstGroup));
        assertTrue(result.contains(secondGroup));
    }

    @Test
    public void getUsersShouldReturnUsersAddedToGroup() {
        //given
        UserId firstUserId = new UserId("tomasz");
        UserId secondUserId = new UserId("anna");
        service.addUserToGroup(firstUserId, ROOT_GROUP);
        service.addUserToGroup(secondUserId, ROOT_GROUP);

        //when
        List<UserId> result = service.getUsers(ROOT_GROUP);

        //then
        assertTrue(result.size() == 2);
        assertTrue(result.contains(firstUserId));
        assertTrue(result.contains(secondUserId));
    }

    @Test
    public void getUsersShouldReturnUserFromChildrenGroups() {
        //given
        UserId firstUserId = new UserId("tomasz");
        UserId secondUserId = new UserId("anna");
        UserId thirdUser = new UserId("janusz");

        GroupId firstGroup = service.create("Subgroup 1", ROOT_GROUP);
        GroupId secondGroup = service.create("Subgroup 2", firstGroup);

        service.addUserToGroup(firstUserId, ROOT_GROUP);
        service.addUserToGroup(secondUserId, firstGroup);
        service.addUserToGroup(thirdUser, secondGroup);

        //when
        List<UserId> result = service.getUsers(ROOT_GROUP);

        //then
        assertTrue(result.size() == 3);
        assertTrue(result.contains(firstUserId));
        assertTrue(result.contains(secondUserId));
        assertTrue(result.contains(thirdUser));
    }
}
