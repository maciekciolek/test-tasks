package com.payments.domain.group;

/**
 * @author mciolek
 */
public class Group {
    private final GroupId id;
    private final String name;
    private final GroupId parentId;

    public Group(GroupId parentId, String name, GroupId id) {
        this.parentId = parentId;
        this.name = name;
        this.id = id;
    }

    public GroupId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GroupId getParentId() {
        return parentId;
    }
}
