package com.samcgardner.codurance.state;

import net.time4j.PrettyTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

public class Message {

    private final String content;
    private final String sender;
    private final LocalDateTime timeSent = LocalDateTime.now();

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public Message(String message, String sender) {
        this.content = message;
        this.sender = sender;
    }

    public String prettyPrintWithoutName() {
        return content
                + " (" + PrettyTime.of(Locale.ENGLISH).print(Duration.between(timeSent, LocalDateTime.now()))
                + " ago)";
    }

    public String prettyPrintWithName() {
        return sender + " - " + prettyPrintWithoutName();
    }

}
