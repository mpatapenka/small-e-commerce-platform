package org.mpatapenka.secp.transform.domain;

import org.mpatapenka.secp.domain.Size;
import org.mpatapenka.secp.entity.SizeEntity;
import org.springframework.stereotype.Component;

@Component
public class SizeTransformer extends NullSafeTransformer<SizeEntity, Size> {
    @Override
    Size safeForward(SizeEntity sizeEntity) {
        Size size = new Size();
        size.setId(sizeEntity.getId());
        size.setNumeric(sizeEntity.getNumeric());
        size.setSymbolic(sizeEntity.getSymbolic());
        size.setArchived(sizeEntity.isArchived());
        return size;
    }

    @Override
    SizeEntity safeBackward(Size size) {
        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(size.getId());
        sizeEntity.setNumeric(size.getNumeric());
        sizeEntity.setSymbolic(size.getSymbolic());
        sizeEntity.setArchived(size.isArchived());
        return sizeEntity;
    }
}