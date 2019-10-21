package by.underwear.shop.transform;

/**
 * Transforms one type to another.
 *
 * @param <S> source type.
 * @param <T> target type.
 */
public interface Transformer<S, T> {
    /**
     * Transforms {@code source} into target type.
     *
     * @param source source object.
     * @return transformed object of target type.
     */
    T transform(S source);
}