package design.patterns.decorator.user.entity;

import design.patterns.decorator.user.profile.Permission;
import design.patterns.decorator.user.profile.Profile;
import design.patterns.decorator.user.profile.ProfileUser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class AdminUser extends UserDecorator {

    public static String PERMISSION = "ADMINISTRATOR_ROLE";

    public AdminUser(User user) {
        super(user);
        ProfileUser profileUser = new ProfileUser();
        Profile profile = new Profile();
        Permission permission = new Permission();
        permission.setRole(AdminUser.PERMISSION);
        profile.setPermissions(new HashSet<>(Arrays.asList(permission)));
        profileUser.setProfiles(new HashSet<>(Arrays.asList(profile)));
        Set<ProfileUser> profilesUser = new HashSet<>(Arrays.asList(profileUser));
        this.profilesUser.addAll(profilesUser);
    }

}
