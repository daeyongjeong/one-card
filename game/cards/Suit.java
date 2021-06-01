package game.cards;

import util.ConsoleColors;

public enum Suit {
    SPADES("♠"), HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        if (this == HEARTS || this == DIAMONDS)
            return ConsoleColors.RED + symbol + ConsoleColors.RESET;
        return symbol;
    }

    public boolean isSuit(Suit suit) {
        return this == suit;
    }
}
