package com.samcgardner.codurance.parser;

import com.samcgardner.codurance.command.*;

public class CommandParser {

    public static Command parseCommand(String rawInput) {

        String[] splitInput = rawInput.split(" ");

        String user = splitInput[0];

        // All typed commands are valid, and the only one-length valid command is reading,
        // so all commands of length one are reads
        if (splitInput.length == 1) {
            return new ReadingCommand(user);
        }

        switch (splitInput[1]) {

            case "follows":
                String followedUser = splitInput[2];
                return new FollowingCommand(user, followedUser);

            case "wall":
                return new WallCommand(user);

            // Same logic: all commands are valid, all other commands are matched, so anything left over must be a post
            default:
                StringBuilder builder = new StringBuilder();
                for (int i = 2; i < splitInput.length; i++) {
                    builder.append(splitInput[i]);
                }
                String message = builder.toString();
                return new PostingCommand(user, message);
        }

    }
}
