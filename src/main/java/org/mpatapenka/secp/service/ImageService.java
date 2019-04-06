package org.mpatapenka.secp.service;

import org.mpatapenka.secp.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface ImageService {
    Image get(long id);
    Image save(MultipartFile image);
    Collection<Image> saveAll(MultipartFile[] images);
    boolean removeAll(Collection<Image> images);
}