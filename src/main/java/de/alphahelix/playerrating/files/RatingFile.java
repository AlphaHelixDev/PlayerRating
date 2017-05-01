package de.alphahelix.playerrating.files;

import de.alphahelix.alphalibary.file.SimpleJSONFile;
import de.alphahelix.alphalibary.mysql.MySQLDatabase;
import de.alphahelix.playerrating.Playerrating;

import java.util.UUID;

public class RatingFile extends SimpleJSONFile {
    public RatingFile() {
        super("plugins/PlayerRating", "rating.json");
    }

    public void addPlayer(UUID player, byte score) {
        if (Playerrating.isMySQL()) {
            MySQLDatabase db = Playerrating.getMySQLDatabase();

            if (db.containsPlayer(player))
                db.update(player, "Rating", Byte.toString((byte) ((getPlayerscore(player) + score) / 2)));
            else
                db.insert(player.toString(), Byte.toString(score));
        } else if (contains(player.toString())) {
            byte curr = getPlayerscore(player);

            setDefault(player.toString(), (byte) ((curr + score) / 2));
        } else setDefault(player.toString(), score);
    }

    public byte getPlayerscore(UUID player) {
        if(Playerrating.isMySQL()) {
            String sql = (String) Playerrating.getMySQLDatabase().getResult("UUID", player.toString(), "Rating");

            if(sql == null) return 5;

            return Byte.parseByte(sql);
        }
        return getValue(player.toString(), Byte.class);
    }
}
