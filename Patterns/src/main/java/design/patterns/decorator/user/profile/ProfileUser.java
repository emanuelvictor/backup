package design.patterns.decorator.user.profile;

import design.patterns.decorator.user.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class ProfileUser {

    Set<User> users = new HashSet<>();

    Set<Profile> profiles = new HashSet<>();

    public ProfileUser() {
        this.profiles.add(new Profile());
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }
}
