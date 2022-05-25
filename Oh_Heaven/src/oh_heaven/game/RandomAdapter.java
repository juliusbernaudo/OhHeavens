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
        return RandomPlayer.randomCard(hand);
    }

    @Override
    public Data info() {
        return data;
    }
}
