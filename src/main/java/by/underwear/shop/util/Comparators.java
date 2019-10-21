package by.underwear.shop.util;

import by.underwear.shop.entity.SizeEntity;

import java.util.Comparator;

public final class Comparators {
    public static final Comparator<Integer> NULL_LAST_INTEGER_COMPARATOR = Comparator.nullsLast(Integer::compareTo);
    public static final Comparator<String> NULL_LAST_STRING_COMPARATOR = Comparator.nullsLast(String::compareTo);

    public static final Comparator<SizeEntity> SIZE_ENTITY_COMPARATOR = Comparator
            .comparing(SizeEntity::getNumeric, NULL_LAST_INTEGER_COMPARATOR)
            .thenComparing(SizeEntity::getSymbolic, NULL_LAST_STRING_COMPARATOR);

    private Comparators() {}
}