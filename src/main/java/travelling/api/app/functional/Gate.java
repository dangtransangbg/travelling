package travelling.api.app.functional;


@FunctionalInterface
public interface Gate<T, R> {
    T consumer(R r);
}
