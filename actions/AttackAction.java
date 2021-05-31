package actions;

import game.TableManager;
import players.Player;

public class AttackAction implements Action {
    private int attackLevel;

    public AttackAction(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    @Override
    public void run(Player player) {
        TableManager.getInstance().addAttackLevel(attackLevel);
    }
}
