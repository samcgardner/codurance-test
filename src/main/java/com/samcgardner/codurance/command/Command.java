package com.samcgardner.codurance.command;

public abstract class Command {

    private final String user;
    private final CommandType type;

    Command(String user, CommandType type) {
        this.user = user;
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public CommandType getType() {
        return type;
    }

    public enum CommandType {
        POSTING,
        READING,
        FOLLOWING,
        WALL
    }

}

