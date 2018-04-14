package com.samcgardner.codurance.command;

public class ReadingCommand extends Command {

    public ReadingCommand(String user) {
        super(user, CommandType.READING);
    }
}
