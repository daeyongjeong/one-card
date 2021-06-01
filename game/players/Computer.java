package game.players;

import java.util.Random;

import game.cards.Card;
import game.cards.Suit;

public class Computer extends Player {
    private Random random;

    public Computer(String name) {
        super(name);
        random = new Random();
    }

    @Override
    protected Card getNextPlayCard() {
        sleep(random.nextInt(500) + 500);
        while (true) {
            int index = 0;
            index = random.nextInt(hand.size());

            if (index >= 0 && index < hand.size()) {
                Card nextPlayCard = hand.get(index);
                if (playableCards.contains(nextPlayCard)) {
                    return nextPlayCard;
                }
            }
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms); // for slow down game speed
        } catch (InterruptedException e) {
        }
    }

    @Override
    public Suit changeSuit() {
        Suit[] suits = Suit.values();
        int index = random.nextInt(suits.length);
        return suits[index];
    }
}
