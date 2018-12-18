package org.mpatapenka.ssp.repository;

import org.mpatapenka.ssp.entity.SizeEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SizeRepository extends IdRepository<SizeEntity> {
    Collection<SizeEntity> findByCategoriesIdOrderByCategoriesNumericSymbolicAsc(Collection<Long> categoriesId);
}