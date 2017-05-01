package de.alphahelix.playerrating.commands;

import de.alphahelix.alphalibary.command.SimpleCommand;
import de.alphahelix.alphalibary.uuid.UUIDFetcher;
import de.alphahelix.playerrating.Playerrating;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RateCommand extends SimpleCommand {
    public RateCommand() {
        super("rate", "Rate a player with a score from 1 to 5", "");
    }

    @Override
    public boolean execute(CommandSender cs, String label, String[] args) {

        if (args.length == 2) {
            if (!args[0].equalsIgnoreCase(cs.getName()))
                if (UUIDFetcher.getUUID(args[0]) != null) {
                    Playerrating.getRatingFile().addPlayer(UUIDFetcher.getUUID(args[0]), Byte.parseByte(args[1]));
                }
            else
                cs.sendMessage(Playerrating.getOptionsFile().getOwnVote());
            return true;
        }

        cs.sendMessage("§7[§3Rating§7] §8/§7rate §8<§7player§8> <§7score §8(§71§8~§75§8)>");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, String s, String[] strings) {
        return null;
    }
}
