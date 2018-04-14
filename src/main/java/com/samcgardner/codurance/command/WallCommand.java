package com.samcgardner.codurance.command;

public class WallCommand extends Command {

    public WallCommand(String user) {
        super(user, CommandType.WALL);
    }

}
