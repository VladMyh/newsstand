package com.newsstand.model.user;

public class User {
    private long id;
    private String username;
    private String email;
    private UserType userType;

    public User() {}

    public User(long id, String username, String email, UserType userType) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userType = userType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!username.equals(user.username)) return false;
        if (!email.equals(user.email)) return false;
        return userType == user.userType;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + username.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + userType.hashCode();
        return result;
    }
}
