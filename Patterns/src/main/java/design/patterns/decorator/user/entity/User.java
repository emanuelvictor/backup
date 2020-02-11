package design.patterns.decorator.user.entity;

import design.patterns.decorator.user.profile.ProfileUser;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public abstract class User {

    String login;

    String name;

    String password;

    Set<ProfileUser> profilesUser = new HashSet<>();

    public User() {
        this.profilesUser.add(new ProfileUser());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ProfileUser> getProfilesUser() {
        return profilesUser;
    }

    public void setProfilesUser(Set<ProfileUser> profilesUser) {
        this.profilesUser = profilesUser;
    }
}
