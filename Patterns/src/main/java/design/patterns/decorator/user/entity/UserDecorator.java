package design.patterns.decorator.user.entity;

/**
 *
 */
public abstract class UserDecorator extends User {

    User user;

    public UserDecorator() {
        super();
    }

    public UserDecorator(User user) {
        this.user = user;
    }
}
