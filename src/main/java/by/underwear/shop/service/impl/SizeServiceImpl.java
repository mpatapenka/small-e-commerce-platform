package by.underwear.shop.service.impl;

import by.underwear.shop.service.SizeService;
import lombok.RequiredArgsConstructor;
import by.underwear.shop.domain.Size;
import by.underwear.shop.entity.SizeEntity;
import by.underwear.shop.repository.SizeRepository;
import by.underwear.shop.transform.Transformer;
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
    public Collection<Size> getAll() {
        return StreamSupport.stream(sizeRepository.findAll().spliterator(), true)
                .map(sizeTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Size> getAll(long categoryId) {
        return sizeRepository.findByCategoriesIdOrderByNumericAsc(Collections.singleton(categoryId)).stream()
                .filter(SizeEntity::getArchived)
                .map(sizeTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Size> getAllActive() {
        return StreamSupport.stream(sizeRepository.findAll().spliterator(), true)
                .filter(SizeEntity::getArchived)
                .map(sizeTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveAll(Collection<Size> sizes) {
//        sizes.parallelStream()
//                .filter(Objects::nonNull)
//                .forEach(size -> sizeRepository.findById(size.getId()).
//                        .ifPresent(sizeEntity -> sizeEntity.setArchived(size.getArchived())),
//                                () -> sizeRepository.save(sizeTransformer.backward(size))));
    }

    @Override
    @Transactional
    public void removeAll(Collection<Long> sizeIds) {
        sizeRepository.deleteAll(sizeRepository.findAllById(sizeIds));
    }
}