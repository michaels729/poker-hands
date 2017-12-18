package my.poker.app.evaluator.hand.analyze;

import java.util.*;
import java.util.stream.Collectors;

public class HandEvaluator {
    public enum State {
        DRAW,
        P1_WIN,
        P2_WIN
    }

    public void printResultMessage(Hand hand1, Hand hand2) {
        System.out.println(getResult(hand1, hand2));
    }

    public String getResult(Hand hand1, Hand hand2) {
        State state = evaluate(hand1, hand2);
        switch (state) {
            case P1_WIN:
                return "P1 won!";
            case P2_WIN:
                return "P2 won!";
            case DRAW:
            default:
                return "Draw...";
        }
    }

    private State evaluate(Hand hand1, Hand hand2) {
        int score1 = score(hand1), score2 = score(hand2);
        if (score1 > score2) {
            return State.P1_WIN;
        } else if (score1 < score2) {
            return State.P2_WIN;
        } else {
            return State.DRAW;
        }
    }

    private int score(Hand hand) {
        int score = getStraightFlushScore(hand);
        if (score > 0) {
            return 8 * (int) Math.pow(15, Hand.HAND_SIZE) + score;
        }

        score = getFourOfAKindScore(hand);
        if (score > 0) {
            return 7 * (int) Math.pow(15, Hand.HAND_SIZE) + score;
        }

        score = getFullHouseScore(hand);
        if (score > 0) {
            return 6 * (int) Math.pow(15, Hand.HAND_SIZE) + score;
        }

        score = getFlushScore(hand);
        if (score > 0) {
            return 5 * (int) Math.pow(15, Hand.HAND_SIZE) + score;
        }

        score = getStraightScore(hand);
        if (score > 0) {
            return 4 * (int) Math.pow(15, Hand.HAND_SIZE) + score;
        }

        score = getThreeOfAKindScore(hand);
        if (score > 0) {
            return 3 * (int) Math.pow(15, Hand.HAND_SIZE) + score;
        }

        score = getTwoPairScore(hand);
        if (score > 0) {
            return 2 * (int) Math.pow(15, Hand.HAND_SIZE) + score;
        }

        score = getOnePairScore(hand);
        if (score > 0) {
            return (int) Math.pow(15, Hand.HAND_SIZE) + score;
        }

        return getHighCardScore(hand);
    }

    int getStraightFlushScore(Hand hand) {
        List<Card> cards = hand.getCards();int score = 0;
        for (int i = 0; i < cards.size()-1; ++i) {
            Card c1 = cards.get(i);
            Card c2 = cards.get(i+1);
            if (!c1.getSuit().equals(c2.getSuit())
                    || (i < cards.size()-2 && c1.getRank().getPoints() + 1 != c2.getRank().getPoints())
                    || (i == cards.size()-2
                        && c2.getRank().equals(Card.Rank.ACE)
                        && !c1.getRank().equals(Card.Rank.KING)
                        && !cards.get(0).getRank().equals(Card.Rank.TWO))) {
                return 0;
            }
            score += Math.pow(Hand.HAND_SIZE, i) * c1.getRank().getPoints();
        }
        if (cards.get(cards.size()-1).getRank().equals(Card.Rank.ACE)
                && cards.get(0).getRank().equals(Card.Rank.TWO)) {
            score += Math.pow(Hand.HAND_SIZE, cards.size()-1) * Card.Rank.getLowAcePoints();
        } else {
            score += Math.pow(Hand.HAND_SIZE, cards.size()-1) * cards.get(cards.size()-1).getRank().getPoints();
        }
        return score;
    }

    int getFourOfAKindScore(Hand hand) {
        return getNOfAKindWithNumGroups(hand, 4, 2, false);
    }

    int getFullHouseScore(Hand hand) {
        return getNOfAKindWithNumGroups(hand, 3, 2, false);
    }

    int getFlushScore(Hand hand) {
        List<Card> cards = hand.getCards();
        int score = 0;
        Card.Suit targetSuit = cards.get(0).getSuit();
        for (int i = 0; i < cards.size(); ++i) {
            Card c = cards.get(i);
            if (!c.getSuit().equals(targetSuit)) {
                return 0;
            }
            score += Math.pow(Hand.HAND_SIZE, i) * c.getRank().getPoints();
        }
        return score;
    }

    int getStraightScore(Hand hand) {
        List<Card> cards = hand.getCards();
        int score = 0;
        for (int i = 0; i < cards.size()-1; ++i) {
            Card c1 = cards.get(i);
            Card c2 = cards.get(i + 1);
            if ((i < cards.size()-2 && c1.getRank().getPoints() + 1 != c2.getRank().getPoints())
                    || (i == cards.size()-2
                        && c2.getRank().equals(Card.Rank.ACE)
                        && !c1.getRank().equals(Card.Rank.KING)
                        && !cards.get(0).getRank().equals(Card.Rank.TWO))) {
                return 0;
            }
            score += c1.getRank().getPoints();
        }
        return score;
    }

    int getThreeOfAKindScore(Hand hand) {
        return getNOfAKindWithNumGroups(hand, 3, 3, false);
    }

    int getTwoPairScore(Hand hand) {
        return getNOfAKindWithNumGroups(hand, 2, 3, true);
    }

    int getOnePairScore(Hand hand) {
        return getNOfAKindWithNumGroups(hand, 2, 4, false);
    }

    int getHighCardScore(Hand hand) {
        return getNOfAKindWithNumGroups(hand, 1, 5, false);
    }

    private class RankCount {
        private Card.Rank rank;
        private int count;

        RankCount(Card.Rank rank, int count) {
            this.rank = rank;
            this.count = count;
        }

        Card.Rank getRank() {
            return rank;
        }

        int getCount() {
            return count;
        }
    }

    private int getNOfAKindWithNumGroups(Hand hand, int n, int numGroups, boolean handleTwoPair) {
        Map<Card.Rank, Integer> counter = new HashMap<>();
        boolean countMet = false;
        int score = 0;
        for (Card c : hand.getCards()) {
            counter.put(c.getRank(), 1 + counter.getOrDefault(c.getRank(), 0));
            if (counter.get(c.getRank()) == n) {
                countMet = true;
            }
        }
        if (!countMet || counter.size() != numGroups || (handleTwoPair && !isValidTwoPair(counter))) {
            return 0;
        }

        PriorityQueue<RankCount> q =
                new PriorityQueue<>(numGroups, (a, b) -> {
                    int dif =  b.getCount() - a.getCount();
                    if (dif == 0) {
                        return b.getRank().getPoints() - a.getRank().getPoints();
                    }
                    return dif;
                });
        q.addAll(counter.entrySet().stream()
                .map(e -> new RankCount(e.getKey(), e.getValue()))
                .collect(Collectors.toList()));
        for (; numGroups > 0; --numGroups) {
            score += Math.pow(15, numGroups-1) * q.poll().getRank().getPoints();
        }
        return score;
    }

    private boolean isValidTwoPair(Map<Card.Rank, Integer> counter) {
        int numPairs = 0;
        for (Map.Entry<Card.Rank, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == 2) {
                ++numPairs;
            }
        }
        return numPairs == 2;
    }
}
