package com.github.sejoung.hystrix.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class FallbackCommandTest {

    @Test
    public void execute(){
        FallbackCommand fallbackCommand = new FallbackCommand();
        assertEquals("Falling back", fallbackCommand.execute());
    }
}
