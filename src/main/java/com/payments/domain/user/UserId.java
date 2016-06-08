package com.payments.domain.user;

/**
 * @author mciolek
 */
public class UserId {
    private final String id;

    public UserId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserId userId = (UserId) o;

        return id.equals(userId.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
