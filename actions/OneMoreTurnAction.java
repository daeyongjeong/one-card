package actions;

import game.PlayManager;
import players.Player;

public class OneMoreTurnAction implements Action {

    @Override
    public void run(Player player) {
        PlayManager.getInstance().setOneMoreTurn();
    }
}
