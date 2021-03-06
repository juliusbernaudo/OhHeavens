package oh_heaven.game;

import ch.aplu.jcardgame.Hand;

public class GameplayFactory {
    private static GameplayFactory instance;
    private IPlayerAdapter playerAdaptor = null;

    // Retrieve character adapter based on the string
    public IPlayerAdapter getPlayerAdaptor(String player, Hand hand){
        if(player.equals("legal")) {
            playerAdaptor = (IPlayerAdapter) new LegalAdapter(hand);
        } else if(player.equals("smart")) {
            playerAdaptor = (IPlayerAdapter) new SmartAdapter(hand);
        } else if(player.equals("human")) {
            playerAdaptor = (IPlayerAdapter) new HumanAdapter(hand);
        } else if(player.equals("random")) {
            playerAdaptor = (IPlayerAdapter) new RandomAdapter(hand);
        }

        return playerAdaptor;
    }

    // static method for accessing GameplayFactory only through this function
    public static synchronized GameplayFactory getInstance() {
        if (instance == null) {
            instance = new GameplayFactory();
        }
        return instance;
    }
}
