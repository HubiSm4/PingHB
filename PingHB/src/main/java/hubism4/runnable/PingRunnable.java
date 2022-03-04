package hubism4.runnable;

import hubism4.PingPlugin;
import hubism4.utils.ChatUtil;
import org.bukkit.plugin.Plugin;

public class PingRunnable implements Runnable {

    PingPlugin plugin = (PingPlugin)PingPlugin.getPlugin(PingPlugin.class);

    public void register() {
        this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)this.plugin, this, 20L, plugin.getConfig().getInt("check_time") * 20L);
    }

    public void run() {
        if (!plugin.getConfig().getBoolean("kicking_enabled"))
            return;
        plugin.getServer().getOnlinePlayers().forEach(p -> {
            if (p.getPing() > plugin.getConfig().getInt("max_ping")); {
                if (!p.hasPermission(plugin.getConfig().getString("bypass_permission"))) {
                    p.kickPlayer(ChatUtil.fixColor(plugin.getConfig().getString("kick_message").replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"))));
                }
            }
        });
    }
}
