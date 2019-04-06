package org.mpatapenka.secp.repository;

import org.mpatapenka.secp.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
}