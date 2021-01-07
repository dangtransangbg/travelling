package travelling.api.app.functional;

@FunctionalInterface
public interface Transform<T> {
    T consume();
}
