package cards;

import actions.AttackAction;

public class JokerCard extends Card {
    public JokerCard() {
        this.action = new AttackAction(5);
    }

    @Override
    public boolean canBePlayedWith(Card criteria) {
        if (isInAttackState() && criteria instanceof GeneralCard)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Joker";
    }
}
