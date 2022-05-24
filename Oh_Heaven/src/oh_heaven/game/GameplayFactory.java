package oh_heaven.game;

public class GameplayFactory {
    private IPlayerAdapter playerAdaptor = null;

    // Retrieve character adapter based on the string
    public IPlayerAdapter getPlayerAdaptor(String player, int id){
        if(player.equals("legal")) {
            playerAdaptor = (IPlayerAdapter) new LegalAdapter(id);
        } else if(player.equals("smart")) {
            playerAdaptor = (IPlayerAdapter) new SmartAdapter(id);
        } else if(player.equals("human")) {
            playerAdaptor = (IPlayerAdapter) new HumanAdapter(id);
        }

        return playerAdaptor;
    }
}
