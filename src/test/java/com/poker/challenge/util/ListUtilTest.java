package com.poker.challenge.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.poker.challenge.util.ListUtil.head;
import static com.poker.challenge.util.ListUtil.tail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListUtilTest {

    @DataProvider
    public Object[][] valuesForHead() {
        return new Object[][]{
                {
                        newArrayList(),
                        null
                },
                {
                        newArrayList("head"),
                        "head"
                },
                {
                        newArrayList("head", "tail"),
                        "head"
                },
                {
                        newArrayList("head", "tail", "moreTail"),
                        "head"
                }
        };
    }

    @Test(dataProvider = "valuesForHead")
    public void shouldGetHeadOfGivenList(List<String> values, String expectedHeadValue) {
        String head = head(values).orElse(null);
        assertThat(head, is(expectedHeadValue));
    }

    @DataProvider
    public Object[][] valuesForTail() {
        return new Object[][]{
                {
                        newArrayList(),
                        0
                },
                {
                        newArrayList("head"),
                        0
                },
                {
                        newArrayList("head", "tail"),
                        1
                },
                {
                        newArrayList("head", "tail", "moreTail"),
                        2
                }
        };
    }

    @Test(dataProvider = "valuesForTail")
    public void shouldGetTailOfGivenList(List<String> values, int expectedResultSize) {

        List<String> result = tail(values);
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(expectedResultSize));
    }
}