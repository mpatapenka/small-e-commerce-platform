package org.mpatapenka.ssp.repository;

import org.mpatapenka.ssp.entity.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends IdRepository<ProductEntity> {
    Collection<ProductEntity> findByCategoryId(long categoryId);
    Collection<ProductEntity> findByCategoryIdAndIsArchived(long categoryId, boolean isArchived, Sort sort);
    Collection<ProductEntity> findBySalePriceIsNotNullAndIsArchivedIsFalse();

    @Query("SELECT DISTINCT p FROM Product AS p JOIN p.productItems AS pi JOIN pi.sizes AS s WHERE " +
            "p.category.id=:categoryId AND " +
            "p.isArchived=false AND " +
            "s.value IN (:sizesValues)")
    Collection<ProductEntity> findByCategoryIdAndSizes(@Param("categoryId") long categoryId, @Param("sizesValues") List<String> sizesValues, Sort sort);
}