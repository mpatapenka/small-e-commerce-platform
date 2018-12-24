package org.mpatapenka.ssp.repository;

import org.mpatapenka.ssp.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}