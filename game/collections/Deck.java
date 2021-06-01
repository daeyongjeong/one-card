package game.collections;

import game.cards.*;

public class Deck extends CardList {
    public Deck() {
        createGeneralCards();
        createJokerCard();
    }

    private void createGeneralCards() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new GeneralCard(suit, rank);
                this.add(card);
            }
        }
    }

    private void createJokerCard() {
        Card joker = new JokerCard();
        this.add(joker);
    }

    public Card draw() {
        return this.getCard();
    }
}
