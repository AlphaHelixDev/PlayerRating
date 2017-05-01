package de.alphahelix.playerrating.files;

import de.alphahelix.alphalibary.file.SimpleFile;
import de.alphahelix.alphalibary.uuid.UUIDFetcher;
import de.alphahelix.playerrating.Playerrating;

public class OptionsFile extends SimpleFile {

    public OptionsFile() {
        super("plugins/PlayerRating", "options.yml");
    }

    @Override
    public void addValues() {
        setDefault("MySQL", false);
        setDefault("Error.rate own", "&7[&3Rating&7] You can't rate yourself!");
        setDefault("Message.own", "&7[&3Rating&7] Your rating is §a[rate] §7star!");
        setDefault("Message.other", "&7[&3Rating&7] §3[player]s' §7rating is §a[rate] §7star!");
    }

    public boolean useMySQL() {
        return getBoolean("MySQL");
    }

    public String getOwnVote() {
        return getColorString("Error.vote own");
    }

    public String getOwn(byte rating) {
        return getColorString("Message.own").replace("[rate]", Byte.toString(rating));
    }

    public String getOther(String player) {
        return getColorString("Message.other").replace("[player]", player).replace("[rate]", Byte.toString(Playerrating.getRatingFile().getPlayerscore(UUIDFetcher.getUUID(player))));
    }
}
