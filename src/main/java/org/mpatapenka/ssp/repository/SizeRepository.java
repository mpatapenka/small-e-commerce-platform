package org.mpatapenka.ssp.repository;

import org.mpatapenka.ssp.entity.SizeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Long> {
    Collection<SizeEntity> findByCategoriesIdOrderByCategoriesNumericSymbolicAsc(Collection<Long> categoriesId);
}