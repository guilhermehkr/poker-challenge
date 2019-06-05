package com.poker.challenge.round;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.poker.challenge.infrastructure.FileReaderTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoundValidatorTest {

    private RoundValidator testInstance;

    @BeforeMethod
    public void setup() {
        testInstance = new RoundValidator();
    }

    @DataProvider
    public Object[][] hands() {
        return new Object[][]{
                {null, true},
                {EMPTY, true},
                {FIRST_HAND_WHITESPACE, true},
                {SECOND_HAND_WHITESPACE, true},
                {FIRST_HAND, false},
                {SECOND_HAND, false}
        };
    }

    @Test(dataProvider = "hands")
    public void shouldReturnIfHandIsValidOrNot(String hand, boolean isInvalid) {

        boolean isAnInvalidRound = testInstance.isAnInvalidRound(hand);
        assertThat(isInvalid, is(isAnInvalidRound));
    }

}