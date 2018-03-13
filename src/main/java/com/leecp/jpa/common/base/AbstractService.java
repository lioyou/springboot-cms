package com.leecp.jpa.common.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T,ID extends Serializable> implements Service<T,ID> {
    private JpaRepository repository;
    public abstract void initRepository();

    @Override
    public T getOne(ID id) {
        return (T)repository.getOne(id);
    }

    @Override
    public T findOne(ID id) {
        return (T)repository.findOne(id);
    }

    @Override
    public <S extends T> S findOne(Example<S> example) {
        return (S)repository.findOne(example);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<T> findAll(Iterable<ID> iterable) {
        return repository.findAll(iterable);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return repository.findAll(example,sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return repository.findAll(example,pageable);
    }

    @Override
    public <S extends T> S save(S s) {
        return (S)repository.save(s);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> iterable) {
        return repository.save(iterable);
    }

    @Override
    public void flush() {
        repository.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return (S)repository.saveAndFlush(s);
    }

    @Override
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return repository.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return repository.exists(example);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void delete(Iterable<? extends T> iterable) {
        repository.delete(iterable);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        repository.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }


    public void setRepository(JpaRepository repository) {
        this.repository = repository;
    }
}
