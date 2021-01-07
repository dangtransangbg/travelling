package travelling.api.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import travelling.api.app.common.constant.MessageConstant;
import travelling.api.app.exception.ObjectNotFoundException;
import travelling.api.app.functional.Gate;
import travelling.api.app.model.response.ListResponse;
import travelling.api.app.repository.BaseRepository;
import travelling.api.app.service.BaseService;
import travelling.api.app.util.CopyModelUtil;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    protected Class<T> tClass;
    protected BaseRepository baseRepository;

    public BaseServiceImpl(BaseRepository baseRepository) {
        tClass = (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        this.baseRepository = baseRepository;
    }

    private String getClassName() {
        return tClass.getSimpleName();
    }

    private String createCode() {
        return getClassName().toUpperCase() + "_NOT_FOUND";
    }

    public <Q> T saveObject(Q q, Function<Q, T> transform) {
        return (T) baseRepository.save(transform.apply(q));
    }

    public <Q> void saveAllObject(List<Q> qs, Function<Q, T> transform) {
        List<T> ts = qs.stream().map(transform::apply).collect(Collectors.toList());
        Iterable<T> is = ts;

        baseRepository.saveAll(is);
    }

    public void saveAllObject(List<T> qs) {
        Iterable<T> is = qs;

        baseRepository.saveAll(is);
    }

    public <Q> void updateObject(ID id, Q q, Function<Q, T> transform) {
        T oldObject = findById(id);
        T newObject = transform.apply(q);
        CopyModelUtil.copyOldToNewModel(oldObject, newObject);

        baseRepository.save(newObject);
    }

    public T findById(ID id) {
        Optional<T> t = baseRepository.findById(id);

        t.orElseThrow(() -> new ObjectNotFoundException(MessageConstant.getValue(createCode())));

        return t.get();
    }

    public <P> P getObject(ID id, Function<T, P> transform) {
        T t = findById(id);

        return transform.apply(t);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public <P> long countAllByCondition(P p, Gate<Specification<T>, P> filter) {
        return baseRepository.count(filter.consumer(p));
    }

    public <P> P getObjectByCondition(P p, Gate<Specification<T>, P> filter, Function<T, P> transform) {
        Optional<T> baseObject = baseRepository.findOne(filter.consumer(p));

        return baseObject.isPresent() ? transform.apply(baseObject.get()) : null;
    }

    @Override
    public <P> T getObjectByCondition(P p, Gate<Specification<T>, P> filter) {
        Optional<T> baseObject = baseRepository.findOne(filter.consumer(p));

        return baseObject.isPresent() ? baseObject.get() : null;
    }

    public <P, Q> ListResponse<P> getAllObjectByCondition(Q q, Gate<Specification<T>, Q> filter, Function<T, P> transform, Pageable pageable) {
        return toListResponse(filter.consumer(q), transform, pageable);
    }

    @Override
    public <P, Q> ListResponse<P> getAllObjectByCondition(Q q, Gate<Specification<T>, Q> filter, Function<T, P> transform) {
        return toListResponse(filter.consumer(q), transform, null);
    }

    @Override
    public <Q> List<T> getAllObjectByCondition(Q q, Gate<Specification<T>, Q> filter) {
        return baseRepository.findAll(filter.consumer(q));
    }

    public <P> ListResponse<P> getAllOObject(Function<T, P> transform, Pageable pageable) {
        return toListResponse(transform, pageable);
    }

    @Override
    public <P> ListResponse<P> getAllOObject(Function<T, P> transform) {
        return toListResponse(transform, null);
    }

    @Override
    public void deleteObject(T t) {
        baseRepository.delete(t);
    }

    @Override
    public void deleteObjectById(ID id) {
        baseRepository.deleteById(id);
    }

    private <P> ListResponse<P> toListResponse(Specification<T> filter, Function<T, P> transform, Pageable pageable) {
        List<P> listItem;
        long totalItem;
        if (pageable == null) {
            List<T> page = baseRepository.findAll(filter);
            listItem = page.stream().map(transform::apply).collect(Collectors.toList());
            totalItem = baseRepository.count(filter);

            return ListResponse.of(totalItem, listItem);
        }

        Page<T> page = baseRepository.findAll(filter, pageable.previousOrFirst());
        listItem = page.getContent().stream().map(transform::apply).collect(Collectors.toList());
        totalItem = page.getTotalPages();

        return ListResponse.of(totalItem, listItem);
    }

    private <P> ListResponse<P> toListResponse(Function<T, P> transform, Pageable pageable) {
        List<P> listItem;
        long totalItem;

        if (pageable == null) {
            List<T> page = baseRepository.findAll();
            listItem = page.stream().map(transform::apply).collect(Collectors.toList());
            totalItem = baseRepository.count();

            return ListResponse.of(totalItem, listItem);
        }
        Page<T> page = baseRepository.findAll(pageable);
        listItem = page.get().map(transform::apply).collect(Collectors.toList());
        totalItem = page.getTotalElements();


        return ListResponse.of(totalItem, listItem);
    }


}
