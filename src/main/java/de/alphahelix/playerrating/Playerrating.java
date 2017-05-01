package de.alphahelix.playerrating;

import de.alphahelix.alphalibary.mysql.MySQLAPI;
import de.alphahelix.alphalibary.mysql.MySQLDatabase;
import de.alphahelix.playerrating.commands.CheckCommand;
import de.alphahelix.playerrating.commands.RateCommand;
import de.alphahelix.playerrating.files.OptionsFile;
import de.alphahelix.playerrating.files.RatingFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Playerrating extends JavaPlugin {

    private static Playerrating instance;
    private static MySQLDatabase database;

    private static RatingFile ratingFile;
    private static OptionsFile optionsFile;

    private static boolean mySQL;

    @Override
    public void onEnable() {
        instance = this;

        ratingFile = new RatingFile();
        optionsFile = new OptionsFile();

        mySQL = optionsFile.useMySQL();

        if(mySQL) {
            for (MySQLAPI api : MySQLAPI.getMysqlDBs()) {
                api.initMySQLAPI();
                database = new MySQLDatabase("PlayerRating", api.getDatabase());
                break;
            }

            database.create(
                    database.createColumn("UUID", MySQLAPI.MySQLDataType.VARCHAR, 50),
                    database.createColumn("Rating", MySQLAPI.MySQLDataType.TEXT, 5000000));
        }

        new RateCommand();
        new CheckCommand();
    }

    public static Playerrating getInstance() {
        return instance;
    }

    public static RatingFile getRatingFile() {
        return ratingFile;
    }

    public static OptionsFile getOptionsFile() {
        return optionsFile;
    }

    public static MySQLDatabase getMySQLDatabase() {
        return database;
    }

    public static boolean isMySQL() {
        return mySQL;
    }
}
