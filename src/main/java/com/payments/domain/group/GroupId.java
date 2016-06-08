package com.payments.domain.group;

/**
 * @author mciolek
 */
public class GroupId {
    private final String id;

    public GroupId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupId groupId = (GroupId) o;

        return id.equals(groupId.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "GroupId(" + id + ")";
    }
}
