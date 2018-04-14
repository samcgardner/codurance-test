package com.samcgardner.codurance.command;

public class FollowingCommand extends Command {

    private final String followedUser;

    public FollowingCommand(String user, String followedUser) {
        super(user, CommandType.FOLLOWING);
        this.followedUser = followedUser;
    }

    public String getFollowedUser() {
        return followedUser;
    }
}
