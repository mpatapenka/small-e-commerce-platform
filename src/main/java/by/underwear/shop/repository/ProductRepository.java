package by.underwear.shop.repository;

import by.underwear.shop.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
//    Collection<ProductEntity> findByCategoryId(long categoryId);
//    Collection<ProductEntity> findByCategoryIdAndArchived(long categoryId, boolean archived, Sort sort);
//    Collection<ProductEntity> findBySalePriceIsNotNullAndArchivedIsFalse();
//
//    @Query("SELECT DISTINCT p FROM Product AS p JOIN p.productItems AS pi JOIN pi.sizes AS s WHERE " +
//            "p.category.id=:categoryId AND " +
//            "p.isArchived=false AND " +
//            "s.value IN (:sizesValues)")
//    Collection<ProductEntity> findByCategoryIdAndSizes(@Param("categoryId") long categoryId, @Param("sizesValues") List<String> sizesValues, Sort sort);
}