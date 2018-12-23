package org.mpatapenka.ssp.service;

import org.mpatapenka.ssp.entity.ImageEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface ImageService {
    Resource loadAsResource(long id);
    ImageEntity store(MultipartFile image);
    Collection<ImageEntity> store(MultipartFile[] images);
    boolean remove(Iterable<ImageEntity> images);
}
