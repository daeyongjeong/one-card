package actions;

import game.PlayManager;
import players.Player;

public class JumpAction implements Action {

    @Override
    public void run(Player player) {
        PlayManager.getInstance().setJumpTurn();
    }
}
