package ru.kuznetsov.seabattle.client.core;

import ru.kuznetsov.seabattle.client.util.Pair;

public interface ActionProvider {
    /**
     * Receive pair action and content from outside source
     * @return pair action and content
     */
    Pair<Action, String> receiveAction();

    void sendResponse(String content);
}
