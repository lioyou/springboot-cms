package com.leecp.jpa.common.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public interface Service<T,ID extends Serializable> {
    public T getOne(ID id);

    public T findOne(ID id);

    public <S extends T> S findOne(Example<S> example);

    public List<T> findAll();

    public List<T> findAll(Sort sort);

    public Page<T> findAll(Pageable pageable);

    public List<T> findAll(Iterable<ID> iterable);

    public <S extends T> List<S> findAll(Example<S> example);

    public <S extends T> List<S> findAll(Example<S> example, Sort sort);

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    public <S extends T> S save(S s);

    public <S extends T> List<S> save(Iterable<S> iterable);

    public void flush();

    public <S extends T> S saveAndFlush(S s);

    public boolean exists(ID id);

    public long count();

    public <S extends T> long count(Example<S> example);

    public <S extends T> boolean exists(Example<S> example);

    public void delete(ID id);

    public void delete(T t);

    public void delete(Iterable<? extends T> iterable);

    public void deleteAll();

    public void deleteInBatch(Iterable<T> iterable);

    public void deleteAllInBatch();
}
