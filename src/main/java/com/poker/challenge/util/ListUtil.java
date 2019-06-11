package com.poker.challenge.util;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

public class ListUtil {

    public static <T> Optional<T> head(List<T> list) {

        if (isEmpty(list)) {
            return empty();
        }

        T head = emptyIfNull(list)
                .subList(0, 1)
                .get(0);

        return ofNullable(head);
    }

    public static <T> List<T> tail(List<T> list) {
        if (isEmpty(list)) {
            return emptyList();
        }
        return emptyIfNull(list).subList(1, list.size());
    }
}