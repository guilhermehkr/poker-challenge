package com.poker.challenge.infrastructure;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class FileReaderTest {

    public static final String EMPTY = "";
    private static final String LINES_FORMAT = "%s " + lineSeparator() + " %s";

    public static final String FIRST_HAND = "9C 9D 8D 7C 3C 2S KD TH 9H 8H";
    public static final String SECOND_HAND = "6C 5H AS 4H 7S 2S KD 7H 2C AC";

    public static final String FIRST_HAND_WHITESPACE = "   9C 9D 8D 7C 3C 2S KD TH 9H 8H  ";
    public static final String SECOND_HAND_WHITESPACE = "    6C 5H AS 4H 7S 2S KD 7H 2C AC    ";

    private FileReader testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new FileReader();
    }

    @DataProvider
    public Object[][] inputs() {
        return new Object[][]{
                {null},
                {new ByteArrayInputStream(EMPTY.getBytes())}
        };
    }

    @Test(dataProvider = "inputs")
    public void shouldReturnEmptyCollectionGivenNoHands(InputStream input) throws IOException {

        List<String> lines = testInstance.readFileFrom(input);
        assertThat(lines, is(empty()));
    }

    @DataProvider
    public Object[][] hands() {
        return new Object[][]{
                {format(LINES_FORMAT, FIRST_HAND, SECOND_HAND).getBytes()},
                {format(LINES_FORMAT, FIRST_HAND_WHITESPACE, SECOND_HAND_WHITESPACE).getBytes()}
        };
    }

    @Test(dataProvider = "hands")
    public void shouldReadFileSuccessfullyGivenTwoHands(byte[] hands) throws IOException {

        ByteArrayInputStream inputSpy = spy(new ByteArrayInputStream(hands));
        List<String> lines = testInstance.readFileFrom(inputSpy);

        assertThat(lines, is(not(empty())));
        assertThat(lines, hasSize(2));
        assertThat(lines, hasItems(FIRST_HAND, SECOND_HAND));

        verify(inputSpy).close();
    }
}