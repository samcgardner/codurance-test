package com.samcgardner.codurance.state;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class User {

    private final List<Message> messages = new LinkedList<>();

    Set<String> getSubscriptions() {
        return subscriptions;
    }

    private final Set<String> subscriptions = new HashSet<>();

    List<Message> getMessages() {
        return messages;
    }

    void addMessage(Message message) {
        messages.add(message);
    }

    void addSubscription(String followedUser) {
        subscriptions.add(followedUser);
    }
}
