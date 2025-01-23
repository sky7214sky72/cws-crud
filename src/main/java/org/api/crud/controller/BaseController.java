package org.api.crud.controller;

import java.util.List;
import org.api.crud.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseController<T, ID> {

  protected abstract BaseService<T, ID> getService();

  @PostMapping
  public ResponseEntity<T> create(@RequestBody T entity) {
    return ResponseEntity.ok(getService().save(entity));
  }

  @GetMapping("/{id}")
  public ResponseEntity<T> read(@PathVariable ID id) {
    return getService().findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<T>> readAll() {
    return ResponseEntity.ok(getService().findAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
    if (getService().findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(getService().save(entity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable ID id) {
    if (getService().findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    getService().deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
