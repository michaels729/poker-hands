package my.poker.app.evaluator.hand.analyze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFourOfAKind {
    private static HandEvaluator eval;

    @BeforeClass
    public static void beforeClass() {
        eval = new HandEvaluator();
    }

    static Hand createFourOfAKindHand1() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEART)); // 5
        cards.add(new Card(Card.Rank.NINE, Card.Suit.CLUB)); // 10
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEART)); // 10
        cards.add(new Card(Card.Rank.NINE, Card.Suit.DIAMOND)); // 10
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.SPADE)); // 5
        return new Hand(cards); // 40
    }

    static Hand createFourOfAKindHand2() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUB)); // 11
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.HEART)); // 2
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.HEART)); // 11
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.SPADE)); // 11
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.DIAMOND)); // 5
        return new Hand(cards); // 40
    }

    static Hand createFourOfAKindHand3() {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card(Card.Rank.TWO, Card.Suit.CLUB));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.DIAMOND));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEART));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.SPADE));
        return new Hand(cards);
    }

    @Test
    public void testNotFourOfAKind() {
        Assert.assertTrue(eval.getFourOfAKindScore(TestHighCard.createHighCardHand1()) == 0);
        Assert.assertTrue(eval.getFourOfAKindScore(TestOnePair.createOnePairHand1()) == 0);
        Assert.assertTrue(eval.getFourOfAKindScore(TestTwoPair.createTwoPairHand1()) == 0);
        Assert.assertTrue(eval.getFourOfAKindScore(TestThreeOfAKind.createThreeOfAKindHand1()) == 0);
        Assert.assertTrue(eval.getFourOfAKindScore(TestFullHouse.createFullHouseHand1()) == 0);
        Assert.assertTrue(eval.getFourOfAKindScore(TestStraight.createStraightHand1()) == 0);
        Assert.assertTrue(eval.getFourOfAKindScore(TestFlush.createFlushHand1()) == 0);
    }

    @Test
    public void testRankPriority() {
        Assert.assertTrue(eval.getFourOfAKindScore(createFourOfAKindHand1()) > eval.getFourOfAKindScore(createFourOfAKindHand2()));
    }

    @Test
    public void testScore() {
        Assert.assertTrue(eval.getFourOfAKindScore(createFourOfAKindHand1()) > eval.getFourOfAKindScore(createFourOfAKindHand3()));
    }

    @Test
    public void testDraw() {
        Assert.assertTrue(eval.getFourOfAKindScore(createFourOfAKindHand1()) == eval.getFourOfAKindScore(createFourOfAKindHand1()));
    }
}
