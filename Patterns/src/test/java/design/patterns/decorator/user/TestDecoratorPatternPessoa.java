package design.patterns.decorator.user;

import design.patterns.decorator.user.entity.AdminUser;
import design.patterns.decorator.user.entity.AttendantUser;
import design.patterns.decorator.user.entity.DefaultUser;
import design.patterns.decorator.user.entity.User;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 *
 */
public class TestDecoratorPatternPessoa {


    @Test
    public void testPeoplesInstance() {

        User user = new DefaultUser();

        user.getProfilesUser().forEach(p -> p.getProfiles().forEach(profile -> profile.getPermissions().forEach(permission -> Assert.isTrue(permission.getRole().equals(DefaultUser.PERMISSION)))));

        user = new AttendantUser(user);

        user.getProfilesUser().forEach(p -> p.getProfiles().forEach(profile -> profile.getPermissions().forEach(permission ->
                        Assert.isTrue(permission.getRole().equals(DefaultUser.PERMISSION) || permission.getRole().equals(AttendantUser.PERMISSION))
        )));

        user = new AdminUser(user);

        user.getProfilesUser().forEach(p -> p.getProfiles().forEach(profile -> profile.getPermissions().forEach(permission ->
                        Assert.isTrue(permission.getRole().equals(DefaultUser.PERMISSION) || permission.getRole().equals(AttendantUser.PERMISSION) || permission.getRole().equals(AdminUser.PERMISSION))
        )));

    }
}
