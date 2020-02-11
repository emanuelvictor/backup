package design.patterns.decorator.user.profile;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Profile {



    private Set<Permission> permissions = new HashSet<>();

    public Profile() {
        this.permissions.add(new Permission());
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
