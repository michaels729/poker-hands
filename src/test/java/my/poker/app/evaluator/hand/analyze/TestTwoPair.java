package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestTwoPair {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createTwoPairHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUB)); // 10
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEART)); // 10
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND)); // 8
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.HEART)); // 8
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.SPADE)); // 4
        return new Hand(cards); // 40
    }

    static Hand createTwoPairHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUB)); // 12
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEART)); // 4
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.SPADE)); // 4
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEART)); // 12
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND)); // 8
        return new Hand(cards); // 40
    }

    static Hand createTwoPairHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TWO, Card.Suit.CLUB));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.DIAMOND));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.SPADE));
        return new Hand(cards);
    }

    @Test
    public void testNotTwoPair() {
        Assert.assertTrue(eval.getTwoPairScore(TestHighCard.createHighCardHand1()) == 0);
        Assert.assertTrue(eval.getTwoPairScore(TestOnePair.createOnePairHand1()) == 0);
    }

    @Test
    public void testRankPriority() {
        Assert.assertTrue(eval.getTwoPairScore(createTwoPairHand1()) < eval.getTwoPairScore(createTwoPairHand2()));
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getTwoPairScore(createTwoPairHand1()) > eval.getTwoPairScore(createTwoPairHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getTwoPairScore(createTwoPairHand1()) == eval.getTwoPairScore(createTwoPairHand1()));
    }
}
