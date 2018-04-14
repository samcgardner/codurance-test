package com.samcgardner.codurance.command;

public class PostingCommand extends Command {

    private final String message;

    public PostingCommand(String user, String message) {
        super(user, CommandType.POSTING);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
