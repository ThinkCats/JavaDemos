/**
 * Created by wang on 15-2-28.
 */
@FunctionalInterface
public interface MyFunction<T extends Integer,E extends Integer> {
    public int apply(T t, E e);
}
