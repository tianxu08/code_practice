package ood3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private static final Random random = new Random();
	
	private final List<Card> cards = new ArrayList<Card>();
	private int dealtIndex = 0;

	/*
	 * 1 A
	 * 2
	 * ...
	 * 11 J
	 * 12 Q 
	 * 13 K
	 */
	
	public Deck() {
		// constructor
		for(int i = 1; i <= 13; i ++) {
			for(Suit suit : Suit.values()) {
				cards.add(new Card(i, suit));
			}
		}
	}
	
	public void shuffle() {
		for(int i = 0; i < cards.size(); i ++) {
			int j = random.nextInt(cards.size() - i) + i;
			Card card1 = cards.get(i);
			Card card2 = cards.get(j);
			cards.set(i, card2);
			cards.set(j, card1);
		}
	}
	
	// get the number of remaining cards
	private int remainingCards() {
		return cards.size() - dealtIndex;
	}
	
	//  发牌  number 张 
	public Card[] dealHand(int number) {
		if (remainingCards() < number) {
			return null;
		}
		Card[] cards = new Card[number];
		for(int i = 0; i < number; i ++) {
			cards[i]  = dealCard();
		}
		return cards;
	}
	
	// 发牌 1 张
	public Card dealCard() {
		return remainingCards() == 0 ? null : cards.get(dealtIndex ++);
	}
}
