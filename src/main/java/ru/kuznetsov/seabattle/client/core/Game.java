package ru.kuznetsov.seabattle.client.core;

import ru.kuznetsov.seabattle.client.util.Pair;

public class Game {
    private final ActionProvider actionProvider;
    private final Player player;

    public Game(ActionProvider actionProvider, Player player) {
        this.actionProvider = actionProvider;
        this.player = player;
    }

    public void start() {
        Action currentAction = Action.START;

        while (currentAction != Action.STOP) {
            Pair<Action, String> actionContentPair = actionProvider.receiveAction();
            currentAction = actionContentPair.left();
            actionProvider.sendResponse(doActon(actionContentPair));
        }
    }

    private String doActon(Pair<Action, String> actionContentPair) {
        return switch (actionContentPair.left()) {
            case NAME -> player.name();
            case DISPLAY_MESSAGE -> {
                player.displayContent(actionContentPair.right());
                yield "";
            }
            case START -> null;
            case WAIT -> null;
            case STOP -> null;
            case NEXT_POINT -> String.valueOf(player.selectPoint());
            case WIN -> null;
            case FAIL -> null;
            case INVALID_ACTION -> null;
        };
    }
}
