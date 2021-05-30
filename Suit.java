public enum Suit {
    SPADES("♠"), HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣");

    private final String Symbol;

    Suit(String Symbol) {
        this.Symbol = Symbol;
    }

    public String getSymbol() {
        return Symbol;
    }

    public boolean isSuit(Suit suit) {
        if (this == suit)
            return true;
        return false;
    }
}
