package org.mpatapenka.ssp.service.impl;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Size;
import org.mpatapenka.ssp.entity.SizeEntity;
import org.mpatapenka.ssp.repository.SizeRepository;
import org.mpatapenka.ssp.service.SizeService;
import org.mpatapenka.ssp.transform.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    private final Transformer<SizeEntity, Size> sizeTransformer;

    @Override
    @Transactional(readOnly = true)
    public Collection<Size> getAll() {
        return StreamSupport.stream(sizeRepository.findAll().spliterator(), true)
                .map(sizeTransformer::forward)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Size> getAll(long categoryId) {
        return sizeRepository.findByCategoriesIdOrderByCategoriesNumericSymbolicAsc(Collections.singleton(categoryId)).stream()
                .map(sizeTransformer::forward)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void archive(long sizeId) {
        sizeRepository.findById(sizeId)
                .ifPresent(sizeEntity -> sizeEntity.setArchived(true));
    }

    @Override
    @Transactional
    public void remove(long sizeId) {
        sizeRepository.findById(sizeId)
                .ifPresent(sizeRepository::delete);
    }
}