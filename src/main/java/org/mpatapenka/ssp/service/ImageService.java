package org.mpatapenka.ssp.service;

import org.mpatapenka.ssp.entity.ImageEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Resource loadAsResource(String path);
    List<ImageEntity> uploadImages(List<MultipartFile> imageFiles);
    void removeImages(Iterable<ImageEntity> imageEntities);
}
