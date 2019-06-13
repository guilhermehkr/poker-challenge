package com.poker.challenge.round;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;

public class RoundResultTest {

    @DataProvider
    public Object[][] results() {
        Supplier<RoundResult> tieFunction = () -> RoundResult.Tie;
        return new Object[][]{
                {
                        2,
                        1,
                        null,
                        RoundResult.PlayerOne
                },
                {
                        1,
                        2,
                        null,
                        RoundResult.PlayerTwo
                },
                {
                        1,
                        1,
                        tieFunction,
                        RoundResult.Tie
                }
        };
    }

    @Test(dataProvider = "results")
    public void shouldDecideHowTheWinnerIs(Integer playerOneCard, Integer playerTwoCard,
                                           Supplier<RoundResult> tieFunction, RoundResult expectedResult) {

        RoundResult result = RoundResult.decideWhoTheWinnerIs(
                playerOneCard,
                playerTwoCard,
                tieFunction
        );

        MatcherAssert.assertThat(result, is(expectedResult));
    }
}