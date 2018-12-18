package org.mpatapenka.ssp.transform;

public interface Transformer<FROM, TO> {
    TO forward(FROM from);
    FROM backward(TO to);
}