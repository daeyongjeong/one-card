package game.actions;

import game.Game;

public class AttackAction implements Action {
    private int attackLevel;

    public AttackAction(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    @Override
    public void run(Game context) {
        context.addAttackLevel(attackLevel);
    }

    @Override
    public String toString() {
        return "damage level of " + attackLevel;
    }
}
