package oh_heaven.game;

// Oh_Heaven.java

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class Oh_Heaven extends CardGame {
	

  
  final String trumpImage[] = {"bigspade.gif","bigheart.gif","bigdiamond.gif","bigclub.gif"};

  static public int seed = 30006;
  static Random random;
  private static ArrayList<String> playerTypes;
  
  // return random Enum value
  public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
      int x = random.nextInt(clazz.getEnumConstants().length);
      return clazz.getEnumConstants()[x];
  }

  // return random Card from Hand
  public static Card randomCard(Hand hand){
      int x = random.nextInt(hand.getNumberOfCards());
      return hand.get(x);
  }
 
  // return random Card from ArrayList
  public static Card randomCard(ArrayList<Card> list){
      int x = random.nextInt(list.size());
      return list.get(x);
  }
  
  private void dealingOut(ArrayList<IPlayerAdapter> players, int nbPlayers, int nbCardsPerPlayer) { // IPlayerAdapter[] players
	  Hand pack = deck.toHand(false);
	  // pack.setView(Oh_Heaven.this, new RowLayout(hideLocation, 0));
	  for (int i = 0; i < nbCardsPerPlayer; i++) {
		  for (int j=0; j < nbPlayers; j++) {
			  if (pack.isEmpty()) return;
			  Card dealt = randomCard(pack);
		      // System.out.println("Cards = " + dealt);
		      dealt.removeFromHand(false);
			  // hands[j].insert(dealt, false);
		      players.get(j).info().getHand().insert(dealt, false);
			  // dealt.transfer(hands[j], true);
		  }
	  }
  }
  
  public boolean rankGreater(Card card1, Card card2) {
	  return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
  }
	 
  private final String version = "1.0";
  public final int nbPlayers = 4;
  public static int nbStartCards = 2;
  public static int nbRounds = 3;
  public final int madeBidBonus = 10;
  private final int handWidth = 400;
  private final int trickWidth = 40;
  private final Deck deck = new Deck(CardType.Suit.values(), CardType.Rank.values(), "cover");
  private final Location[] handLocations = {
			  new Location(350, 625),
			  new Location(75, 350),
			  new Location(350, 75),
			  new Location(625, 350)
	  };
  private final Location[] scoreLocations = {
			  new Location(575, 675),
			  new Location(25, 575),
			  new Location(575, 25),
			  // new Location(650, 575)
			  new Location(575, 575)
	  };
  private final Location trickLocation = new Location(350, 350);
  private final Location textLocation = new Location(350, 450);
  private final int thinkingTime = 2000;
  private GameplayFactory gameplayFactory = new GameplayFactory();
  private ArrayList<IPlayerAdapter> players;
  private Location hideLocation = new Location(-500, - 500);
  private Location trumpsActorLocation = new Location(50, 50);
  private static boolean enforceRules=false;

  public void setStatus(String string) { setStatusText(string); }


Font bigFont = new Font("Serif", Font.BOLD, 36);

private void initScore() {
	 for (int i = 0; i < nbPlayers; i++) {
		 // scores[i] = 0;
		 String text = "[" + String.valueOf(players.get(i).info().getScore()) + "]" + String.valueOf(players.get(i).info().getTrick()) + "/" + String.valueOf(players.get(i).info().getBid());
		 players.get(i).info().setScoreActor( new TextActor(text, Color.WHITE, bgColor, bigFont));
		 addActor(players.get(i).info().getScoreActor(), scoreLocations[i]);
	 }
  }

private void updateScore(int player) {
	removeActor(players.get(player).info().getScoreActor());
	String text = "[" + String.valueOf(players.get(player).info().getScore()) + "]" + String.valueOf(players.get(player).info().getTrick()) + "/" + String.valueOf(players.get(player).info().getBid());
	players.get(player).info().setScoreActor(new TextActor(text, Color.WHITE, bgColor, bigFont));
	addActor(players.get(player).info().getScoreActor(), scoreLocations[player]);
}

private void initScores() {
	 for (int i = 0; i < nbPlayers; i++) {
		 players.get(i).info().setScore(0);
	 }
}

private void updateScores() {
	 for (int i = 0; i < nbPlayers; i++) {
		 players.get(i).info().updateScores(madeBidBonus);
	 }
}

private void initTricks() {
	 for (int i = 0; i < nbPlayers; i++) {
		 players.get(i).info().setTrick(0);
	 }
}

private void initBids(CardType.Suit trumps, int nextPlayer) {
	int total = 0;
	for (int i = nextPlayer; i < nextPlayer + nbPlayers; i++) {
		int iP = i % nbPlayers;
		players.get(iP).info().setBid(nbStartCards / 4 + random.nextInt(2));
		total += players.get(iP).info().getBid();
	 }
	 if (total == nbStartCards) {  // Force last bid so not every bid possible
		 int iP = (nextPlayer + nbPlayers) % nbPlayers;
		 if (players.get(iP).info().getBid() == 0) {
			 players.get(iP).info().setBid(1);
		 } else {
			 players.get(iP).info().addBid(random.nextBoolean() ? -1 : 1);
		 }
	 }
	// for (int i = 0; i < nbPlayers; i++) {
	// 	 bids[i] = nbStartCards / 4 + 1;
	//  }
 }

private Card selected;

private void initPlayers() {
	players = new ArrayList<>();
	for (int i = 0; i < nbPlayers; i++) {
		players.add(gameplayFactory.getInstance().getPlayerAdaptor(playerTypes.get(i), new Hand(deck)));
	}
}

private void initRound() {

	dealingOut(players, nbPlayers, nbStartCards);
	 for (int i = 0; i < nbPlayers; i++) {
		 players.get(i).info().getHand().sort(Hand.SortType.SUITPRIORITY, true);
	 }

	 // Set up human player for interaction
	for (int i = 0; i < nbPlayers; i++) {
		players.get(i).info().setLayout(new RowLayout(handLocations[i], handWidth));
		players.get(i).info().getLayout().setRotationAngle(90 * i);
		// layouts[i].setStepDelay(10);
		players.get(i).info().getHand().setView(this, players.get(i).info().getLayout());

		players.get(i).info().getHand().setTargetArea(new TargetArea(trickLocation));
		players.get(i).info().getHand().draw();
	}
//	for (int i = 1; i < nbPlayers; i++) // This code can be used to visually hide the cards in a hand (make them face down)
//	  hands[i].setVerso(true);			// You do not need to use or change this code.
//	 End graphics
 }

private void playRound() {
	// Select and display trump suit
	final CardType.Suit trumps = randomEnum(CardType.Suit.class);
	final Actor trumpsActor = new Actor("sprites/"+trumpImage[trumps.ordinal()]);
	addActor(trumpsActor, trumpsActorLocation);
	// End trump suit
	Hand trick;
	int winner;
	Card winningCard;
	CardType.Suit lead;
	int nextPlayer = random.nextInt(nbPlayers); // randomly select player to lead for this round
	initBids(trumps, nextPlayer);


    // initScore();
    for (int i = 0; i < nbPlayers; i++) updateScore(i);
	for (int i = 0; i < nbStartCards; i++) {
		//adds to players stats
		for (int l = 0; l < nbPlayers; l++) {
			players.get(l).info().setTrump(trumps);
			players.get(l).info().setLeader(nextPlayer);
			players.get(l).info().setPlayerNum(l);
		}
		trick = new Hand(deck);
    	selected = null;
    	// if (false) {
		if (players.get(nextPlayer) instanceof HumanAdapter) {
			// hands[0].setTouchEnabled(true);
			((HumanAdapter) players.get(nextPlayer)).setSelected(null);
			players.get(nextPlayer).info().getHand().setTouchEnabled(true);
			selected = players.get(nextPlayer).move();
			setStatus("Player " + nextPlayer + " double-click on card to follow.");
			while (null == (selected = players.get(nextPlayer).move())) delay(100);
		} else if (players.get(nextPlayer) instanceof RandomAdapter) {
			setStatusText("Random Player " + nextPlayer + " thinking...");
			delay(thinkingTime);
			selected = players.get(nextPlayer).move();
		}
		else if (players.get(nextPlayer) instanceof LegalAdapter) {
			setStatusText("Legal Player " + nextPlayer + " thinking...");
			delay(thinkingTime);
			selected = players.get(nextPlayer).move();
		}
		else if (players.get(nextPlayer) instanceof SmartAdapter) {
			setStatusText("Smart Player " + nextPlayer + " thinking...");
			delay(thinkingTime);
			selected = players.get(nextPlayer).move();
		}
        // Lead with selected card
		trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards()+2)*trickWidth));
		trick.draw();
		selected.setVerso(false);
		// No restrictions on the card being lead
		lead = (CardType.Suit) selected.getSuit();
		selected.transfer(trick, true); // transfer to trick (includes graphic effect)
		winner = nextPlayer;
		winningCard = selected;

		// adds to players stats
		for (int k = 0; k < nbPlayers; k++) {
			players.get(k).info().setLeadSuit(lead);
		}

		// End Lead
		for (int j = 1; j < nbPlayers; j++) {
			// add played cards to smart players data
			for (int k = 0; k < nbPlayers; k++) {
				if (players.get(k) instanceof SmartAdapter) {
					//players.get(k).info().addToCurrentlyPlayed(selected);
					players.get(k).info().setCurrentWinningCard(winningCard);
				}
			}
			if (++nextPlayer >= nbPlayers) nextPlayer = 0;  // From last back to first
			selected = null;
			// if (false) {
	        if (players.get(nextPlayer) instanceof HumanAdapter) {
				((HumanAdapter) players.get(nextPlayer)).setSelected(null);
				players.get(nextPlayer).info().getHand().setTouchEnabled(true);
				selected = players.get(nextPlayer).move();
	    		setStatus("Player " + nextPlayer + " double-click on card to follow.");
	    		while (null == (selected = players.get(nextPlayer).move())) delay(100);
			} else if (players.get(nextPlayer) instanceof RandomAdapter) {
				setStatusText("Random Player " + nextPlayer + " thinking...");
				delay(thinkingTime);
				selected = players.get(nextPlayer).move();
			}
			else if (players.get(nextPlayer) instanceof LegalAdapter) {
				setStatusText("Legal Player " + nextPlayer + " thinking...");
				delay(thinkingTime);
				selected = players.get(nextPlayer).move();
			}
			else if (players.get(nextPlayer) instanceof SmartAdapter) {
				setStatusText("Smart Player " + nextPlayer + " thinking...");
				delay(thinkingTime);
				selected = players.get(nextPlayer).move();
			}
	        // Follow with selected card
		        trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards()+2)*trickWidth));
				trick.draw();
				selected.setVerso(false);  // In case it is upside down
				// Check: Following card must follow suit if possible
					if (selected.getSuit() != lead && players.get(nextPlayer).info().getHand().getNumberOfCardsWithSuit(lead) > 0) {
						 // Rule violation
						 String violation = "Follow rule broken by player " + nextPlayer + " attempting to play " + selected;
						 System.out.println(violation);
						 if (enforceRules) 
							 try {
								 throw(new BrokeRuleException(violation));
								} catch (BrokeRuleException e) {
									e.printStackTrace();
									System.out.println("A cheating player spoiled the game!");
									System.exit(0);
								}  
					 }
				// End Check
				 selected.transfer(trick, true); // transfer to trick (includes graphic effect)
				 System.out.println("winning: " + winningCard);
				 System.out.println(" played: " + selected);
				 // System.out.println("winning: suit = " + winningCard.getSuit() + ", rank = " + (13 - winningCard.getRankId()));
				 // System.out.println(" played: suit = " +    selected.getSuit() + ", rank = " + (13 -    selected.getRankId()));
				 if ( // beat current winner with higher card
					 (selected.getSuit() == winningCard.getSuit() && rankGreater(selected, winningCard)) ||
					  // trumped when non-trump was winning
					 (selected.getSuit() == trumps && winningCard.getSuit() != trumps)) {
					 System.out.println("NEW WINNER");
					 winner = nextPlayer;
					 winningCard = selected;
				 }
			// End Follow
		}
		delay(600);
		trick.setView(this, new RowLayout(hideLocation, 0));
		trick.draw();		
		nextPlayer = winner;
		setStatusText("Player " + nextPlayer + " wins trick.");
		players.get(nextPlayer).info().addTrick(1);
		updateScore(nextPlayer);
	}
	removeActor(trumpsActor);
}

  public Oh_Heaven()
  {
	super(700, 700, 30);
    setTitle("Oh_Heaven (V" + version + ") Constructed for UofM SWEN30006 with JGameGrid (www.aplu.ch)");
    setStatusText("Initializing...");
	initPlayers();
    initScores();
    initScore();
    for (int i=0; i <nbRounds; i++) {
		initTricks();
		initRound();
		playRound();
		updateScores();
    }
    for (int i=0; i <nbPlayers; i++) updateScore(i);
    int maxScore = 0;
    for (int i = 0; i <nbPlayers; i++) if (players.get(i).info().getScore() > maxScore) maxScore = players.get(i).info().getScore();
    Set <Integer> winners = new HashSet<Integer>();
    for (int i = 0; i <nbPlayers; i++) if (players.get(i).info().getScore() == maxScore) winners.add(i);
    String winText;
    if (winners.size() == 1) {
    	winText = "Game over. Winner is player: " +
    			winners.iterator().next();
    }
    else {
    	winText = "Game Over. Drawn winners are players: " +
    			String.join(", ", winners.stream().map(String::valueOf).collect(Collectors.toSet()));
    }
    addActor(new Actor("sprites/gameover.gif"), textLocation);
    setStatusText(winText);
    refresh();
  }

  public static void main(String[] args)
  {
	  // System.out.println("Working Directory = " + System.getProperty("user.dir"));
	  final Properties properties;
	  if (args == null || args.length == 0) {
		  properties = PropertiesLoader.loadPropertiesFile(null);
	  } else {
		  properties = PropertiesLoader.loadPropertiesFile(args[0]);
	  }
	  seed = PropertiesLoader.loadSeed(properties);
	  nbStartCards = PropertiesLoader.loadNbStartCards(properties);
	  nbRounds = PropertiesLoader.loadRounds(properties);
	  enforceRules = PropertiesLoader.loadEnforceRules(properties);
	  playerTypes = PropertiesLoader.loadPlayers(properties);


	  random = new Random(seed);

	  new Oh_Heaven();
  }

}
