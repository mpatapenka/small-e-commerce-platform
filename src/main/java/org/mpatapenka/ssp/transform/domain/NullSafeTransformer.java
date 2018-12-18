package org.mpatapenka.ssp.transform.domain;

import org.mpatapenka.ssp.transform.Transformer;

import javax.annotation.Nonnull;

abstract class NullSafeTransformer<FROM, TO> implements Transformer<FROM, TO> {
    @Override
    public TO forward(FROM from) {
        if (from == null) {
            return null;
        }
        return safeForward(from);
    }

    abstract TO safeForward(@Nonnull FROM from);

    @Override
    public FROM backward(TO to) {
        if (to == null) {
            return null;
        }
        return safeBackward(to);
    }

    abstract FROM safeBackward(@Nonnull TO to);
}