package design.patterns.proxy.fragment.configuration;

/**
 * Created by emanuelvictor on 30/10/15.
 */
public class Configuration {

    private static Configuration ourInstance = new Configuration();

    public static Configuration getInstance() {
        return ourInstance;
    }

    Position position = Position.PORTRAIT;

    Scale scale = Scale.SMARTPHONE;

    private Configuration() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

}
