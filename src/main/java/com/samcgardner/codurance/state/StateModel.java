package com.samcgardner.codurance.state;

import com.samcgardner.codurance.command.FollowingCommand;
import com.samcgardner.codurance.command.PostingCommand;
import com.samcgardner.codurance.command.ReadingCommand;
import com.samcgardner.codurance.command.WallCommand;
import com.samcgardner.codurance.exceptions.MissingUserException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StateModel {

    private final Map<String, User> users = new HashMap<>();

    public void applyPostCommand(PostingCommand parsedCommand) {
        Message message = new Message(parsedCommand.getMessage(), parsedCommand.getUser());
        users.computeIfAbsent(parsedCommand.getUser(), __ -> new User()).addMessage(message);
    }

    public List<Message> read(ReadingCommand parsedCommand) throws MissingUserException {
        validateUser(parsedCommand.getUser());

        User desiredUser = users.get(parsedCommand.getUser());
        return desiredUser.getMessages();
    }

    public void applyFollowCommand(FollowingCommand parsedCommand) throws MissingUserException {
        validateUser(parsedCommand.getUser());
        validateUser(parsedCommand.getFollowedUser());

        User desiredUser = users.get(parsedCommand.getUser());

        desiredUser.addSubscription(parsedCommand.getFollowedUser());
    }

    public List<Message> readSubscriptions(WallCommand parsedCommand) throws MissingUserException {
        validateUser(parsedCommand.getUser());

        User desiredUser = users.get(parsedCommand.getUser());

        return desiredUser.getSubscriptions().stream()
                // We don't need to ensure that users in the subscription are valid as we don't allow the creation
                // of subscriptions for users who don't exist
                .map(user -> users.get(user))
                .flatMap(user -> user.getMessages().stream())
                .sorted(Comparator.comparing(Message::getTimeSent))
                .collect(Collectors.toList());
    }

    private void validateUser(String user) throws MissingUserException {
        if(!users.containsKey(user)) throw new MissingUserException("User " + user + " doesn't exist yet");
    }
}
