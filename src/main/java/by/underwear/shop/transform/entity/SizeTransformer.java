//package by.inlove.shop.transform.impl;
//
//import by.inlove.shop.domain.Size;
//import by.inlove.shop.entity.SizeEntity;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SizeTransformer extends NullSafeTransformer<SizeEntity, Size> {
//    @Override
//    Size safeForward(SizeEntity sizeEntity) {
//        Size size = new Size();
//        size.setId(sizeEntity.getId());
//        size.setNumeric(sizeEntity.getNumeric());
//        size.setSymbolic(sizeEntity.getSymbolic());
////        size.setArchived(sizeEntity.isArchived());
//        return size;
//    }
//
//    @Override
//    SizeEntity safeBackward(Size size) {
//        SizeEntity sizeEntity = new SizeEntity();
//        sizeEntity.setId(size.getId());
//        sizeEntity.setNumeric(size.getNumeric());
//        sizeEntity.setSymbolic(size.getSymbolic());
////        sizeEntity.setArchived(size.isArchived());
//        return sizeEntity;
//    }
//}