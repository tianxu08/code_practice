package ood3;

import java.util.ArrayList;
import java.util.List;


/*
 * BlackJack score rules:
 * 2-10 scores its face value
 * J, Q, K score 10
 * A scores either 1 or 11
 */

/*
 * BlackJackHand extends Hand
 * -- score: choose the best among all possible values
 * -- isBlackJack: boolean
 */
public class BlackJackHand extends Hand {
	@Override
	public int score() {
		List<Integer> scores = possibleScores();
		int maxUnder = Integer.MIN_VALUE;  // max score <= 21
		int minOver = Integer.MAX_VALUE;  // min score > 21
		
		for(int score: scores) {
			if (score > 21 && score < minOver) {
				minOver = score;
			} else if (score <= 21 && score > maxUnder) {
				maxUnder = score;
			}
		}
		return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
	}
	
	private List<Integer> possibleScores() {
		List<Integer> scores = new ArrayList<Integer>();
		for(Card card: cards) {
			addCardToScoreList(card, scores);
		}
		return scores;
	}
	
	private void addCardToScoreList(Card card, List<Integer> scores) {
		if (scores.isEmpty()) {
			for(int score: getScores(card)) {
				scores.add(score);
			}
		} else {
			final int length = scores.size();
			for(int i = 0; i < length; i ++) {
				int oldScore = scores.get(i);
				int[] toAdd = getScores(card);
				scores.set(i, oldScore + toAdd[0]);
				for(int j= 1; j < toAdd.length; j ++) {
					scores.add(oldScore + toAdd[j]);
				}
			}
		}
	}
	
	private int[] getScores(Card card) {
		if (card.value() > 1) {
			return new int[] {Math.min(card.value(), 10)};
		} else {
			// Ace
			return new int[]{1,11};
		}
	}
	
	public boolean busted() {
		return score() > 21;
	}
	
	public boolean is21() {
		return score() == 21;
	}
	
	public boolean isBlackJack() {
		if (cards.size() != 2) {
			return false;
		}
		Card first = cards.get(0);
		Card second = cards.get(1);
		
		return (isAce(first) && isFaceCard(second)) ||
				(isAce(second) && isFaceCard(first));
	}
	
	private static boolean isAce(Card c) {
		return c.value() == 1;
	}
	
	private static boolean isFaceCard(Card c) {
		return c.value() >= 11 && c.value() <= 13;
	}
}
