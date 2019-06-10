package com.poker.challenge.infrastructure;

import com.poker.challenge.service.GameService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class PokerGameCommandLineRunnerTest {

    @InjectMocks
    private PokerGameCommandLineRunner testInstance;

    @Mock
    private GameService gameService;

    @Mock
    private FileReader fileReader;

    @BeforeMethod
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldRunAndCallServicesSuccessfully() throws Exception {

        List<String> lines = new ArrayList<>();
        given(fileReader.readFileFrom(System.in)).willReturn(lines);

        testInstance.run();

        verify(fileReader).readFileFrom(System.in);
        verify(gameService).start(lines);
    }
}