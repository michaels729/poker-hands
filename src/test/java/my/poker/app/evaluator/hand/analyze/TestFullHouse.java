package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFullHouse {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createFullHouseHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEART)); // 5
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUB)); // 10
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEART)); // 10
        cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMOND)); // 10
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.SPADE)); // 5
        return new Hand(cards); // 40
    }

    static Hand createFullHouseHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUB)); // 12
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART)); // 2
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEART)); // 12
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.SPADE)); // 12
        cards.add(new Card(Card.Rank.TWO, Card.Suit.DIAMOND)); // 2
        return new Hand(cards); // 40
    }

    static Hand createFullHouseHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TWO, Card.Suit.CLUB));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.DIAMOND));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.SPADE));
        return new Hand(cards);
    }

    @Test
    public void testNotFullHouse() {
        Assert.assertTrue(eval.getFullHouseScore(TestHighCard.createHighCardHand1()) == 0);
        Assert.assertTrue(eval.getFullHouseScore(TestOnePair.createOnePairHand1()) == 0);
        Assert.assertTrue(eval.getFullHouseScore(TestTwoPair.createTwoPairHand1()) == 0);
        Assert.assertTrue(eval.getFullHouseScore(TestThreeOfAKind.createThreeOfAKindHand1()) == 0);
        Assert.assertTrue(eval.getFullHouseScore(TestStraight.createStraightHand1()) == 0);
        Assert.assertTrue(eval.getFullHouseScore(TestFlush.createFlushHand1()) == 0);
    }

    @Test
    public void testRankPriority() {
        Assert.assertTrue(eval.getFullHouseScore(createFullHouseHand1()) < eval.getFullHouseScore(createFullHouseHand2()));
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getFullHouseScore(createFullHouseHand1()) > eval.getFullHouseScore(createFullHouseHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getFullHouseScore(createFullHouseHand1()) == eval.getFullHouseScore(createFullHouseHand1()));
    }
}
