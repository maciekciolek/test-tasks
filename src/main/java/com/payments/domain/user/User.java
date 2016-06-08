package com.payments.domain.user;

/**
 * @author mciolek
 */
public class User {
    private final UserId id;
    private final String login;

    public User(UserId id, String login) {
        this.id = id;
        this.login = login;
    }

    public UserId getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
