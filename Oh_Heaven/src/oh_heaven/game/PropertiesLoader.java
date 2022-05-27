package oh_heaven.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesLoader {
    public static final String DEFAULT_DIRECTORY_PATH = "properties/";
    public static Properties loadPropertiesFile(String propertiesFile) {
        if (propertiesFile == null) {
            try (InputStream input = new FileInputStream( DEFAULT_DIRECTORY_PATH + "runmode.properties")) {

                Properties prop = new Properties();

                // load a properties file
                prop.load(input);

                propertiesFile = DEFAULT_DIRECTORY_PATH + prop.getProperty("current_mode");
                System.out.println(propertiesFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try (InputStream input = new FileInputStream(propertiesFile)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int loadSeed(Properties properties) {
        int seed = Integer.parseInt(properties.getProperty("seed"));
        return seed;
    }
    public static int loadNbStartCards(Properties properties) {
        int nbStartCards = Integer.parseInt(properties.getProperty("nbStartCards"));
        return nbStartCards;
    }

    public static int loadRounds(Properties properties) {
        int rounds = Integer.parseInt(properties.getProperty("rounds"));
        return rounds;
    }

    public static boolean loadEnforceRules(Properties properties) {
        boolean enforceRules = Boolean.parseBoolean(properties.getProperty("enforceRules"));
        return enforceRules;
    }

    public static ArrayList<String> loadPlayers(Properties properties) {
        final int playersCount = 4;
        ArrayList<String> players = new ArrayList<String>();;

        for (int i = 0; i < playersCount; i++) {
            String player = properties.getProperty("players." + i);
            players.add(player);
        }

        return players;
    }
}
