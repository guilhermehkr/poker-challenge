package com.poker.challenge.transformer;

public interface Transformer<Input, Output> {

    Output transform(Input input);
}
