package game.cards;

public enum Rank {
    ACE(14), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12),
    KING(13);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public boolean higherThan(Rank rank) {
        return this.value > rank.value;
    }

    @Override
    public String toString() {
        switch (this) {
            case ACE:
            case JACK:
            case QUEEN:
            case KING:
                return this.name().substring(0, 1);
            default:
                return Integer.toString(value);
        }
    }
}
