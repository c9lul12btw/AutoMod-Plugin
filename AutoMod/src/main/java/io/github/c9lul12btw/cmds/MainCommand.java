package io.github.c9lul12btw.cmds;

import io.github.c9lul12btw.Main;
import io.github.c9lul12btw.listeners.ChatListener;
import io.github.c9lul12btw.utils.LoggerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            LoggerUtil.logMessage("&cYou must be a player to run this command!");
            return true;
        }
        Player player = (Player) sender;
        if (args[0].equalsIgnoreCase("reload")) {
            if (player.hasPermission("automod.reload")) {
                Main.getInstance().reloadConfig();
                ChatListener.updatePhrases();
                LoggerUtil.logMessage("[AutoMod] Reloaded configuration.");
            } else {
                player.sendMessage("&cYou have no permission to reload.");
            }
        }
        return true;
    }

}
