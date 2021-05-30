public class Card {
    CardType type;
    Suit suit;
    Rank rank;

    public Card() {

    }

    public boolean isJoker() {
        if (type == CardType.JOKER)
            return true;
        return false;
    }

    public boolean isAttackCard() {
        if (type == CardType.JOKER)
            return true;
        if (rank == Rank.ACE || rank == Rank.TWO)
            return true;
        return false;
    }

    public boolean isDefenceCard() {
        if (type == CardType.JOKER)
            return true;
        if (rank == Rank.ACE || rank == Rank.TWO || rank == Rank.THREE)
            return true;
        return false;
    }

    public boolean isActionCard() {
        if (rank == Rank.KING || rank == Rank.QUEEN || rank == Rank.JACK || rank == Rank.SEVEN)
            return true;
        return false;
    }
}
