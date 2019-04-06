package org.mpatapenka.secp.transform.domain;

import org.mpatapenka.secp.transform.Transformer;

abstract class NullSafeTransformer<FROM, TO> implements Transformer<FROM, TO> {
    @Override
    public TO forward(FROM from) {
        if (from == null) {
            return null;
        }
        return safeForward(from);
    }

    abstract TO safeForward(FROM from);

    @Override
    public FROM backward(TO to) {
        if (to == null) {
            return null;
        }
        return safeBackward(to);
    }

    abstract FROM safeBackward(TO to);
}