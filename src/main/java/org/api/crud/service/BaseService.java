package org.api.crud.service;

import java.util.List;
import java.util.Optional;
import org.api.crud.repository.BaseRepository;

public abstract class BaseService<T, ID> {

  protected abstract BaseRepository<T, ID> getRepository();

  public T save(T entity) {
    return getRepository().save(entity);
  }

  public Optional<T> findById(ID id) {
    return getRepository().findById(id);
  }

  public List<T> findAll() {
    return getRepository().findAll();
  }

  public void deleteById(ID id) {
    getRepository().deleteById(id);
  }
}
