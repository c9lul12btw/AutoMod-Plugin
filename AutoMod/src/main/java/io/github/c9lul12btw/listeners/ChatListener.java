package io.github.c9lul12btw.listeners;

import io.github.c9lul12btw.Main;
import io.github.c9lul12btw.utils.LoggerUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Set;

public class ChatListener implements Listener {

    private static final HashMap<String[], String> checks = new HashMap<>();

    public ChatListener() {
        updatePhrases();
    }

    public static void updatePhrases() {
        checks.clear();
        Set<String> wordLists = Main.getInstance().getConfig().getConfigurationSection("data").getKeys(false);
        FileConfiguration config = Main.getInstance().getConfig();
        for (String key : wordLists) {
            String[] words = key.split(",");
            String value = config.getString("data." + key);
            checks.put(words, value);
        }
    }

    @EventHandler
    public void AsyncChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        for (String words[] : checks.keySet()) {
            int i = 0;
            for (String word : words) {
                if (message.contains(word.toLowerCase())) {
                    i++;
                }
            }
            if (i == words.length) {
                e.setCancelled(true);
                LoggerUtil.sendMessage(player, "&7[&eAUTOMOD&7] " + checks.get(words));
                LoggerUtil.sendMessage(player, "&7- If this wasn't what you were looking for, turn me &coff &7in settings!");
                LoggerUtil.logMessage("[AutoMod] Replied automatically to " + player.getName());
                LoggerUtil.logMessage("[AutoMod] Original message: " + message);
                break;
            }
        }
    }
}
