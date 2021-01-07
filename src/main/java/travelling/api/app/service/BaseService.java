package travelling.api.app.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import travelling.api.app.functional.Gate;
import travelling.api.app.model.response.ListResponse;

import java.util.List;
import java.util.function.Function;

public interface BaseService<T, ID> {
    <Q> T saveObject(Q q, Function<Q, T> transform);

    <Q> void saveAllObject(List<Q> qs, Function<Q, T> transform);

    void saveAllObject(List<T> qs);

    <Q> void updateObject(ID id, Q q, Function<Q, T> transform);

    T findById(ID id);

    <P> P getObject(ID id, Function<T, P> transform);

    List<T> findAll();

    <P> long countAllByCondition(P p, Gate<Specification<T>, P> filter);

    <P> P getObjectByCondition(P p, Gate<Specification<T>, P> filter, Function<T, P> transform);

    <P> T getObjectByCondition(P p, Gate<Specification<T>, P> filter);

    <P, Q> ListResponse<P> getAllObjectByCondition(Q q, Gate<Specification<T>, Q> filter, Function<T, P> transform, Pageable pageable);

    <P, Q> ListResponse<P> getAllObjectByCondition(Q q, Gate<Specification<T>, Q> filter, Function<T, P> transform);

    <Q> List<T> getAllObjectByCondition(Q q, Gate<Specification<T>, Q> filter);

    <P> ListResponse<P> getAllOObject(Function<T, P> transform, Pageable pageable);

    <P> ListResponse<P> getAllOObject(Function<T, P> transform);

    void deleteObject(T t);

    void deleteObjectById(ID id);
}
