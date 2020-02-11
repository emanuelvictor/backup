package design.patterns.decorator.user.profile;

import design.patterns.decorator.user.entity.DefaultUser;

/**
 *
 */
public class Permission {

    String role = DefaultUser.PERMISSION;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
