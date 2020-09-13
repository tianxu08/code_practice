package ood3;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	
	/*
	 * 如何表示手牌［玩家手中的牌？］
	 * Card[] or List<Card>
	 * score()
	 * addCard(Card)
	 */
	
	final List<Card> cards = new ArrayList<Card>();
	
	/*
	 * get the score of the cards
	 */
	public int score() {
		int score = 0;
		for(Card card: cards) {
			score += card.value();
		}
		return score;
	}
	
	public void addCards(Card[] c) {
		for(Card card: c) {
			cards.add(card);
		}
	}
	
	public int size() {
		return cards.size();
	}
}
