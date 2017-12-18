package my.poker.app.evaluator.hand.analyze;

public class Card {
    public enum Rank {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE;

        public int getPoints() {
            return ordinal() + 2;
        }

        public static int getLowAcePoints() {
            return 1;
        }
    }

    public enum Suit {
        CLUB,
        DIAMOND,
        HEART,
        SPADE
    }

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}
