package org.mpatapenka.ssp.repository;

import org.mpatapenka.ssp.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
}