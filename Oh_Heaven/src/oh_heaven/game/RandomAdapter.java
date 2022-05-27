package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class RandomAdapter implements IPlayerAdapter{
    public Data data;
    private RandomPlayer randomPlayer;
    private Hand hand;

    public RandomAdapter(Hand hand){
        data = new Data();
        data.setHand(hand);
        this.hand = hand;
        randomPlayer = new RandomPlayer();
    }

    @Override
    public Card move() {
        // choose a card randomly from hand
        if (!hand.isEmpty()) {
            return randomPlayer.randomCard(hand);
        }
        else {
            return null;
        }
    }

    @Override
    public Data info() {
        return data;
    }
}
