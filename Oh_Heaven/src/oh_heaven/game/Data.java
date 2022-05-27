package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.CardGame;
import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.RowLayout;
import ch.aplu.jgamegrid.Actor;

public class Data extends CardGame {
    private Hand hand;
    private int score = 0;
    private int trick = 0;
    private int bid;
    private Actor scoreActor = new Actor();
    private RowLayout layout;
    private int leader;
    private Oh_Heaven.Suit trump;
    private Oh_Heaven.Suit leadSuit;
    private int playerNum;
    //private Hand currentlyPlayed;
    private Card currentWinningCard;

    public Data() {
    }

    public Card getCurrentWinningCard() {return currentWinningCard;}

    public void setCurrentWinningCard(Card currentWinningCard) {this.currentWinningCard = currentWinningCard;}

   // public Hand getCurrentlyPlayed() {return currentlyPlayed;}

    //public void addToCurrentlyPlayed(Card card) {this.currentlyPlayed.insert(card, false);}

    public Oh_Heaven.Suit getLeadSuit() {return leadSuit;}

    public void setLeadSuit(Oh_Heaven.Suit leadSuit) {this.leadSuit = leadSuit;}

    public int getPlayerNum() {return playerNum;}

    public void setPlayerNum(int playerNum) {this.playerNum = playerNum;}

    public void setLeader(int leader) {this.leader = leader;}

    public int getLeader() {return leader;}

    public Oh_Heaven.Suit getTrump() {return trump;}

    public void setTrump(Oh_Heaven.Suit trump) {this.trump = trump;}

    public int getNumberOfCards() { return hand.getNumberOfCards();}

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {this.score += score;}

    public void updateScores(int madeBidBonus) {
        addScore(trick);
        if (trick == bid) addScore(madeBidBonus);
    }

    public int getTrick() {
        return trick;
    }

    public void setTrick(int trick) {
        this.trick = trick;
    }

    public void addTrick(int trick) {
        this.trick += trick;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void addBid(int bid) {this.bid += bid;}

    public Actor getScoreActor() {
        return scoreActor;
    }

    public void setScoreActor(Actor scoreActor) {
        this.scoreActor = scoreActor;
    }

    public RowLayout getLayout() {
        return layout;
    }

    public void setLayout(RowLayout layout) {
        this.layout = layout;
    }
}
