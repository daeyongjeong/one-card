import java.util.LinkedList;

public class Hand extends LinkedList<Card> {
    public Hand filterWith(Card filterCard, Suit changedSuit) {
        Hand filteredHand = new Hand();
        for (Card card : this) {
            if (card.isJoker())
                filteredHand.add(card);
            if (filterCard.rank == Rank.ACE) {
                if (card.rank == Rank.ACE) {
                    filteredHand.add(card);
                }
            } else if (filterCard.rank == Rank.TWO) {
                if (card.rank == Rank.ACE || card.rank == Rank.TWO) {
                    filteredHand.add(card);
                }
            } else if (filterCard.rank == Rank.SEVEN) {
                if (card.suit == changedSuit) {
                    filteredHand.add(card);
                }
                if (filterCard.rank == card.rank) {
                    filteredHand.add(card);
                }
            } else {
                if (filterCard.suit == card.suit) {
                    filteredHand.add(card);
                }
                if (filterCard.rank == card.rank) {
                    filteredHand.add(card);
                }
            }
        }
        return filteredHand;
    }
}
