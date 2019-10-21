package by.underwear.shop.repository;

import by.underwear.shop.entity.SizeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Long> {
    Collection<SizeEntity> findByCategoriesIdOrderByNumericAsc(Collection<Long> categoriesId);
}