package oh_heaven.game;

import ch.aplu.jcardgame.CardGame;
import ch.aplu.jcardgame.Hand;

public class Data extends CardGame {
    private Hand[] hands;
    private int score;
    private int trick;
    private int bid;
    private boolean enforceRules = false;

    public Data(Hand[] hands){
        this.hands = hands;
    }

    public Hand[] getHands() {
        return hands;
    }

    public void setHands(Hand[] hands) {
        this.hands = hands;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTrick() {
        return trick;
    }

    public void setTrick(int trick) {
        this.trick = trick;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public boolean isEnforceRules() {
        return enforceRules;
    }

    public void setEnforceRules(boolean enforceRules) {
        this.enforceRules = enforceRules;
    }
}
