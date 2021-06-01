package game.players;

import game.cards.Card;
import game.cards.Suit;

public class User extends Player {

    public User(String name) {
        super(name);
    }

    @Override
    protected Card getNextPlayCard() {
        view.showHandWithPlayableCard(hand, playableCards);
        while (true) {
            view.showPromptMessage("Type index for play card.");
            int index = view.getNextInt();

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
            view.showPromptMessage("Type index for change suit.");
            int index = view.getNextInt();

            if (index >= 0 && index < Suit.values().length) {
                return Suit.values()[index];
            }
        }
    }
}