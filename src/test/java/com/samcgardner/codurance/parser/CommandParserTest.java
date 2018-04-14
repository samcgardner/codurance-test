package com.samcgardner.codurance.parser;

import com.samcgardner.codurance.command.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandParserTest {

    @Test
    public void parseFollowingCommand() {
        String testInput = "Sam follows Jim";
        FollowingCommand result = (FollowingCommand) CommandParser.parseCommand(testInput);
        assertEquals("Sam", result.getUser());
        assertEquals("Jim", result.getFollowedUser());
    }

    @Test
    public void parseReadingCommand() {
        String testInput = "Sam";
        ReadingCommand result = (ReadingCommand) CommandParser.parseCommand(testInput);
        assertEquals("Sam", result.getUser());
    }

    @Test
    public void parsePostingCommand() {
        String testInput = "Sam -> apple";
        PostingCommand result = (PostingCommand) CommandParser.parseCommand(testInput);
        assertEquals("Sam", result.getUser());
        assertEquals("apple", result.getMessage());
    }

    @Test
    public void parseWallCommand() {
        String testInput = "Sam wall";
        WallCommand result = (WallCommand) CommandParser.parseCommand(testInput);
        assertEquals("Sam", result.getUser());
    }

}