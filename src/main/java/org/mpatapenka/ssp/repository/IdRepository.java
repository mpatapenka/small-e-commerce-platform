package org.mpatapenka.ssp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdRepository<T> extends CrudRepository<T, Long> {
}