package hubism4;

import hubism4.command.PingCmd;
import hubism4.runnable.PingRunnable;
import hubism4.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8Loading PingHB..."));
        try {
            loadConfig();
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8Plugin loaded &asuccesfully."));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8Version: &71.0"));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8Developer: &7HubiSm4_"));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8GitHub: &7HubiSm4"));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8Discord: &7HubiSm4#0001"));
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8The Plugin can't load! Please contact the developer on Discord."));
            Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8Plugin nie może się załadować! Skontaktuj się z autorem na Discordzie."));
        }
        (new PingRunnable()).register();
        getCommand("ping").setExecutor((CommandExecutor) new PingCmd());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&8Disabling PingHB..."));
    }

    private void loadConfig() {
        if (!this.getDataFolder().exists())
            this.getDataFolder().mkdir();
        if (!(new File(this.getDataFolder(), "config.yml")).exists())
            this.saveDefaultConfig();
    }

}
