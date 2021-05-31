package cards;

import actions.*;
import game.TableManager;

public class GeneralCard extends Card {
    private Suit suit;
    private Rank rank;

    public GeneralCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.action = decideProperAction(rank);
    }

    private Action decideProperAction(Rank rank) {
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
    public boolean canBePlayedWith(Card criteria) {
        if (isInAttackState()) {
            if (criteria instanceof JokerCard) {
                return false;
            } else if (this.hasHigherRankThanOrEqualRankWith((GeneralCard) criteria)) {
                return true;
            }
        } else {
            if (criteria instanceof JokerCard) {
                return true;
            } else if (this.rank == Rank.SEVEN && this.suit == getChangedSuit()) {
                return true;
            } else if (this.rank == ((GeneralCard) criteria).rank || this.suit == ((GeneralCard) criteria).suit) {
                return true;
            }
        }
        return false;
    }

    private Suit getChangedSuit() {
        return TableManager.getInstance().getChangedSuit();
    }

    private boolean hasHigherRankThanOrEqualRankWith(GeneralCard criteria) {
        return this.rank.isHigherThanOrEqualsTo(criteria.rank);
    }

    @Override
    public String toString() {
        return suit.getSymbol() + rank.toString();
    }
}