package com.poker.challenge;

import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.*;

public class PokerCommandLineRunnerTest {

    @InjectMocks
    private PokerCommandLineRunner testInstance;

    @Mock
    private PokerService pokerService;

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
        verify(pokerService).play(lines);
    }
}