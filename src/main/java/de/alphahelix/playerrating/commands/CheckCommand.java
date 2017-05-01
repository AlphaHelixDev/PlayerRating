package de.alphahelix.playerrating.commands;

import de.alphahelix.alphalibary.command.SimpleCommand;
import de.alphahelix.alphalibary.uuid.UUIDFetcher;
import de.alphahelix.playerrating.Playerrating;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CheckCommand extends SimpleCommand {
    public CheckCommand() {
        super("check", "Check your own and others rating", "");
    }

    @Override
    public boolean execute(CommandSender cs, String s, String[] args) {

        if (args.length == 1) {
            if (UUIDFetcher.getUUID(args[0]) != null)
                cs.sendMessage(Playerrating.getOptionsFile().getOther(args[0]));
        } else if (args.length == 0)
            if (cs instanceof Player)
                cs.sendMessage(Playerrating.getOptionsFile().getOwn(Playerrating.getRatingFile().getPlayerscore(UUIDFetcher.getUUID(cs.getName()))));
            else
                cs.sendMessage("§7[§3Rating§7] §8/§7check §8[§7player§8]");

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, String s, String[] strings) {
        return null;
    }
}
