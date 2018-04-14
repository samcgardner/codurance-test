package com.samcgardner.codurance;

import com.samcgardner.codurance.command.*;
import com.samcgardner.codurance.exceptions.MissingUserException;
import com.samcgardner.codurance.parser.CommandParser;
import com.samcgardner.codurance.state.Message;
import com.samcgardner.codurance.state.StateModel;

import java.util.List;
import java.util.Scanner;

public class MainLoop {

    public static void main(String[] args) {

        // Print an empty string so we don't confusingly sit on the last line of output from gradle if running
        // using ./gradlew run
        System.out.println();

        StateModel state = new StateModel();

        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {

            String rawInput = input.nextLine();

            Command parsedCommand = CommandParser.parseCommand(rawInput);

            try {

                switch (parsedCommand.getType()) {

                    case POSTING:
                        state.applyPostCommand((PostingCommand) parsedCommand);
                        break;

                    case READING:
                        List<Message> messages = state.read((ReadingCommand) parsedCommand);
                        messages.forEach(message -> System.out.println(message.prettyPrintWithoutName()));
                        break;

                    case FOLLOWING:
                        state.applyFollowCommand((FollowingCommand) parsedCommand);
                        break;

                    case WALL:
                        List<Message> subscriptionMessages = state.readSubscriptions((WallCommand) parsedCommand);
                        subscriptionMessages.forEach(message -> System.out.println(message.prettyPrintWithName()));
                        break;

                    default:
                        throw new RuntimeException("Programming error, unmatched case in enum");

                }
            } catch (MissingUserException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
