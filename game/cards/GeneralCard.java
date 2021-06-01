package game.cards;

import game.actions.*;
import game.Game;

public class GeneralCard extends Card {
    private Suit suit;
    private Rank rank;

    public GeneralCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.action = getProperAction(rank);
    }

    private Action getProperAction(Rank rank) {
        switch (rank) {
            case KING:
                return new OneMoreTurnAction();
            case QUEEN:
                return new ReverseAction();
            case JACK:
                return new JumpAction();
            case SEVEN:
                return new ChangeAction();
            case TWO:
                return new AttackAction(2);
            case ACE:
                return new AttackAction(3);
            default:
                return null;
        }
    }

    @Override
    public boolean isPlayable(Game context) {
        Card criteria = context.getLastCard();
        if (criteria instanceof JokerCard)
            return !context.isAttackState();
        else
            return isPlayable(context, (GeneralCard) criteria);
    }

    private boolean isPlayable(Game context, GeneralCard criteria) {
        if (context.isAttackState()) {
            if (action instanceof AttackAction && (rank.higherThan(criteria.rank) || rank.equals(criteria.rank)))
                return true;
        } else if (criteria.rank.equals(Rank.SEVEN)) {
            if (rank.equals(criteria.rank) || suit.equals(context.getChangedSuit()))
                return true;
        } else {
            if (rank.equals(criteria.rank) || suit.equals(criteria.suit))
                return true;
        }
        return false;
    }

    public boolean isSevenCard() {
        return rank.equals(Rank.SEVEN);
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return suit.getSymbol() + rank.toString();
    }

}