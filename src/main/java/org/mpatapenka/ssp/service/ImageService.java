package org.mpatapenka.ssp.service;

import org.mpatapenka.ssp.entity.ImageEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface ImageService {
    Resource getAsResource(long id);
    ImageEntity save(MultipartFile image);
    Collection<ImageEntity> saveAll(MultipartFile[] images);
    boolean removeAll(Collection<ImageEntity> images);
}