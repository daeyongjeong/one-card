package players;

import java.util.Random;
import cards.Card;
import cards.Suit;

public class Computer extends Player {

    public Computer(String name) {
        super(name);
    }

    @Override
    protected Card getNextPlayCard() {
        while (true) {
            int index = 0;
            Random random = new Random();
            index = random.nextInt(hand.size());

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
        Suit[] suits = Suit.values();
        Random random = new Random();
        int index = random.nextInt(suits.length);
        return suits[index];
    }
}
