package org.mpatapenka.ssp.transform.domain;

import org.mpatapenka.ssp.domain.Size;
import org.mpatapenka.ssp.entity.SizeEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class SizeTransformer extends NullSafeTransformer<SizeEntity, Size> {
    @Override
    Size safeForward(@Nonnull SizeEntity sizeEntity) {
        Size size = new Size();
        size.setId(sizeEntity.getId());
        size.setNumeric(sizeEntity.getNumeric());
        size.setSymbolic(sizeEntity.getSymbolic());
        size.setArchived(sizeEntity.isArchived());
        return size;
    }

    @Override
    SizeEntity safeBackward(@Nonnull Size size) {
        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(size.getId());
        sizeEntity.setNumeric(size.getNumeric());
        sizeEntity.setSymbolic(size.getSymbolic());
        sizeEntity.setArchived(size.isArchived());
        return sizeEntity;
    }
}