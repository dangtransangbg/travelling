package travelling.api.app.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Mapper {

    default <T, V> List<V> convertToList(Function<T, V> convert, Collection<T> collection) {
        return collection
                .stream()
                .map(convert::apply)
                .collect(Collectors.toList());
    }

    default <T, V> Set<V> convertToSet(Function<T, V> convert, Collection<T> collection) {
        return collection
                .stream()
                .map(convert::apply)
                .collect(Collectors.toSet());
    }

    default <T, V> List<V> convertToListWithFilter(Function<T, V> convert, Collection<T> collection, Predicate<T> filter) {
        return collection
                .stream()
                .filter(filter::test)
                .map(convert::apply)
                .collect(Collectors.toList());
    }
}


