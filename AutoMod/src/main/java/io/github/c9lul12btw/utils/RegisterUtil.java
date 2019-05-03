package io.github.c9lul12btw.utils;

import io.github.c9lul12btw.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;

public class RegisterUtil {

    public static void registerListener(Listener listener) {
        Main.getPlugin(Main.class).getServer().getPluginManager().registerEvents(listener, Main.getPlugin(Main.class));
    }

    public static void registerPermission(Permission perm) {
        Main.getPlugin(Main.class).getServer().getPluginManager().addPermission(perm);
    }

    public static void registerCommand(String commandName, CommandExecutor executor) {
        Main.getPlugin(Main.class).getCommand(commandName).setExecutor(executor);
    }

}
