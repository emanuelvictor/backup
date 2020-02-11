package java8;

/**
 * Created by emanuelvictor on 27/10/15.
 */
@FunctionalInterface
public interface Validador<T> {
    boolean valida(T t);
}
