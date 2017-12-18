package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFlush {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createFlushHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.ACE, Card.Suit.CLUB)); // 14
        cards.add(new Card(Card.Rank.THREE, Card.Suit.CLUB)); // 3
        cards.add(new Card(Card.Rank.JACK, Card.Suit.CLUB)); // 11
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.CLUB)); // 5
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUB)); // 7
        return new Hand(cards); // Total: 40
    }

    static Hand createFlushHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.KING, Card.Suit.SPADE)); // 13
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.SPADE)); // 8
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.SPADE)); // 5
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.SPADE)); // 12
        cards.add(new Card(Card.Rank.TWO, Card.Suit.SPADE)); // 2
        return new Hand(cards); // Total: 40
    }

    static Hand createFlushHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.SIX, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEART));
        return new Hand(cards);
    }

    @Test
    public void testNotFlush() {
        Assert.assertTrue(eval.getFlushScore(TestHighCard.createHighCardHand1()) == 0);
        Assert.assertTrue(eval.getFlushScore(TestOnePair.createOnePairHand1()) == 0);
        Assert.assertTrue(eval.getFlushScore(TestTwoPair.createTwoPairHand1()) == 0);
        Assert.assertTrue(eval.getFlushScore(TestThreeOfAKind.createThreeOfAKindHand1()) == 0);
        Assert.assertTrue(eval.getFlushScore(TestStraight.createStraightHand1()) == 0);
    }

    @Test
    public void testRankPriority() {
        Assert.assertTrue(eval.getFlushScore(createFlushHand1()) > eval.getFlushScore(createFlushHand2()));
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getFlushScore(createFlushHand1()) > eval.getFlushScore(createFlushHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getFlushScore(createFlushHand1()) == eval.getFlushScore(createFlushHand1()));
    }
}
