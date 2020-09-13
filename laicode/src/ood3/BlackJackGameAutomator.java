package ood3;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameAutomator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private Deck deck;
	private BlackJackHand[] hands;
	private static final int HIT_UNTIL = 16;
	
	public BlackJackGameAutomator(int numPlayers) {
		hands = new BlackJackHand[numPlayers];
		for(int i = 0; i < numPlayers; i ++) {
			hands[i] = new BlackJackHand();
		}
	}
	
	public void initializeDeck() {
		deck = new Deck();
		deck.shuffle();
	}
	
	// get two cards first
	public boolean dealInitial() {
		for(BlackJackHand hand: hands) {
			Card[] cards = deck.dealHand(2);
			
			if (cards == null) {
				return false;
			}
			hand.addCards(cards);
		}
		return true;
	}
	
	public List<Integer> getBlackJack() {
		List<Integer> winners = new ArrayList<Integer>();
		for(int i = 0; i < hands.length; i ++) {
			if (hands[i].isBlackJack()) {
				winners.add(i);
			}
		}
		return winners;
	}
	
	public boolean playHand(BlackJackHand hand) {
		while(hand.score() < HIT_UNTIL) {
			Card card = deck.dealCard();
			if (card == null) {
				return false;
			}
			hand.addCards(new Card[] {card});
		}
		return true;
	}
	
	public boolean playAllHands() {
		for(BlackJackHand hand: hands) {
			if (!playHand(hand)) {
				return false;
			}
		}
		return true;
	}

}
