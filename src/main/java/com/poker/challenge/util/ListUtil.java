package com.poker.challenge.util;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

public class ListUtil {

    public final static int FIRST_ELEMENT = 0;
    public final static int FIRST_INDEX = 0;
    public final static int SECOND_INDEX = 1;

    public static <T> Optional<T> head(List<T> list) {

        if (isEmpty(list)) {
            return empty();
        }

        T head = emptyIfNull(list)
                .subList(FIRST_INDEX, SECOND_INDEX)
                .get(FIRST_ELEMENT);

        return ofNullable(head);
    }

    public static <T> List<T> tail(List<T> list) {
        if (isEmpty(list)) {
            return emptyList();
        }
        return emptyIfNull(list)
                .subList(SECOND_INDEX, list.size());
    }
}