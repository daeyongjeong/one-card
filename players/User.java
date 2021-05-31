package players;

import cards.Card;
import cards.Suit;

public class User extends Player {

    public User(String name) {
        super(name);
    }

    @Override
    protected Card getNextPlayCard() {
        view.showHandWithPlayableCard(hand, playableCards);
        while (true) {
            int index = view.promptNextInt("Type index for play card.");

            if (index >= 0 && index < hand.size()) {
                Card nextPlayCard = hand.get(index);
                if (playableCards.contains(nextPlayCard)) {
                    return nextPlayCard;
                }
            }
        }
    }

    @Override
    public Suit changeSuit() {
        view.showAllSuitsWithIndex();
        while (true) {
            int index = view.promptNextInt("Type index for change suit.");

            if (index >= 0 && index < Suit.values().length) {
                Suit changedSuit = Suit.values()[index];
                return changedSuit;
            }
        }
    }
}