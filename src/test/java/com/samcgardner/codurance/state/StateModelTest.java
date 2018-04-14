package com.samcgardner.codurance.state;

import com.samcgardner.codurance.command.FollowingCommand;
import com.samcgardner.codurance.command.PostingCommand;
import com.samcgardner.codurance.command.ReadingCommand;
import com.samcgardner.codurance.command.WallCommand;
import com.samcgardner.codurance.exceptions.MissingUserException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StateModelTest {

    private StateModel state;

    @Before
    public void setupState() {
        state = new StateModel();
    }

    @Test(expected = MissingUserException.class)
    public void forbidsReadingMissingUsers() throws MissingUserException {
        ReadingCommand command = new ReadingCommand("Sam");
        state.read(command);
    }

    @Test
    public void allowsReadingUserMessages() throws MissingUserException {
        PostingCommand post = new PostingCommand("Sam", "Hello world");
        state.applyPostCommand(post);
        ReadingCommand read = new ReadingCommand("Sam");
        List<Message> messages = state.read(read);
        assertEquals(1, messages.size());
        assertEquals("Hello world", messages.get(0).prettyPrintWithoutName().substring(0, 11));
    }

    @Test
    public void allowsReadingUserSubscriptions() throws MissingUserException {
        PostingCommand post = new PostingCommand("Sam", "Hello world");
        PostingCommand otherPost = new PostingCommand("Bob", "Hello even more world");
        state.applyPostCommand(post);
        state.applyPostCommand(otherPost);

        FollowingCommand followingCommand = new FollowingCommand("Sam", "Sam");
        FollowingCommand otherFollowingCommand = new FollowingCommand("Sam", "Bob");
        state.applyFollowCommand(followingCommand);
        state.applyFollowCommand(otherFollowingCommand);

        WallCommand wallCommand = new WallCommand("Sam");
        List<Message> messages = state.readSubscriptions(wallCommand);

        assertEquals(2, messages.size());
        assertEquals("Hello world", messages.get(0).prettyPrintWithoutName().substring(0, 11));
        assertEquals("Hello even more world", messages.get(1).prettyPrintWithoutName().substring(0, 21));
    }

}