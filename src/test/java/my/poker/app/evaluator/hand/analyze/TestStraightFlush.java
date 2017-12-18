package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestStraightFlush {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createStraightFlushHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMOND)); // 10
        cards.add(new Card(Card.Rank.NINE, Card.Suit.DIAMOND)); // 9
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND)); // 8
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMOND)); // 7
        cards.add(new Card(Card.Rank.SIX, Card.Suit.DIAMOND)); // 6
        return new Hand(cards); // 40
    }

    static Hand createStraightFlushHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADE)); // 11
        cards.add(new Card(Card.Rank.KING, Card.Suit.SPADE)); // 13
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.SPADE)); // 12
        cards.add(new Card(Card.Rank.ACE, Card.Suit.SPADE)); // 14
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADE)); // 10
        return new Hand(cards); // 60
    }

    static Hand createStraightFlushHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEART)); // 3
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEART)); // 1
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEART)); // 5
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART)); // 2
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEART)); // 4
        return new Hand(cards); // 15
    }

    @Test
    public void testNotStraightFlush() {
        Assert.assertTrue(eval.getStraightFlushScore(TestHighCard.createHighCardHand1()) == 0);
        Assert.assertTrue(eval.getStraightFlushScore(TestOnePair.createOnePairHand1()) == 0);
        Assert.assertTrue(eval.getStraightFlushScore(TestTwoPair.createTwoPairHand1()) == 0);
        Assert.assertTrue(eval.getStraightFlushScore(TestThreeOfAKind.createThreeOfAKindHand1()) == 0);
        Assert.assertTrue(eval.getStraightFlushScore(TestFullHouse.createFullHouseHand1()) == 0);
        Assert.assertTrue(eval.getStraightFlushScore(TestStraight.createStraightHand1()) == 0);
        Assert.assertTrue(eval.getStraightFlushScore(TestFlush.createFlushHand1()) == 0);
        Assert.assertTrue(eval.getStraightFlushScore(TestFourOfAKind.createFourOfAKindHand1()) == 0);
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getStraightFlushScore(createStraightFlushHand1()) < eval.getStraightFlushScore(createStraightFlushHand2()));
        Assert.assertTrue(eval.getStraightFlushScore(createStraightFlushHand1()) > eval.getStraightFlushScore(createStraightFlushHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getStraightFlushScore(createStraightFlushHand1()) == eval.getStraightFlushScore(createStraightFlushHand1()));
    }

    @Test
    public void testAce() {
        Assert.assertTrue(eval.getStraightFlushScore(createStraightFlushHand2()) != 0);
        Assert.assertTrue(eval.getStraightFlushScore(createStraightFlushHand3()) != 0);
    }
}
