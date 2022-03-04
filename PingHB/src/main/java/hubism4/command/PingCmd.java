package hubism4.command;

import hubism4.PingPlugin;
import hubism4.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCmd implements CommandExecutor {
    PingPlugin plugin = (PingPlugin)PingPlugin.getPlugin(PingPlugin.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Komenda dla graczy!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            String msg = plugin.getConfig().getString("your_ping_cmd");
            assert msg != null;
            msg = msg.replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"));
            msg = msg.replaceAll("%ping%", String.valueOf(p.getPing()));
            msg = msg.replaceAll("%max_ping%", String.valueOf(plugin.getConfig().getInt("max_ping")));
            p.sendMessage(ChatUtil.fixColor(msg));
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (p.hasPermission(plugin.getConfig().getString("reload_permission"))) {
                    String msg = plugin.getConfig().getString("reload_message");
                    assert msg != null;
                    msg = msg.replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"));
                    msg = msg.replaceAll("%max_ping%", String.valueOf(plugin.getConfig().getInt("max_ping")));
                    msg = msg.replaceAll("%permission_use%", plugin.getConfig().getString("ping_permission"));
                    msg = msg.replaceAll("%permission_reload%", plugin.getConfig().getString("reload_permission"));
                    p.sendMessage(ChatUtil.fixColor(msg));
                    plugin.reloadConfig();
                } else {
                    String msg = plugin.getConfig().getString("reload_denied");
                    assert msg != null;
                    msg = msg.replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"));
                    msg = msg.replaceAll("%max_ping%", String.valueOf(plugin.getConfig().getInt("max_ping")));
                    msg = msg.replaceAll("%permission_use%", plugin.getConfig().getString("ping_permission"));
                    msg = msg.replaceAll("%permission_reload%", plugin.getConfig().getString("reload_permission"));
                    p.sendMessage(ChatUtil.fixColor(msg));
                }
                return true;
            }
            if (Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                if (sender == target) {
                    String msg = plugin.getConfig().getString("your_ping_cmd");
                    assert msg != null;
                    msg = msg.replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"));
                    msg = msg.replaceAll("%ping%", String.valueOf(target.getPing()));
                    msg = msg.replaceAll("%max_ping%", String.valueOf(plugin.getConfig().getInt("max_ping")));
                    msg = msg.replaceAll("%player%", target.getName());
                    msg = msg.replaceAll("%permission_use%", plugin.getConfig().getString("ping_permission"));
                    msg = msg.replaceAll("%permission_reload%", plugin.getConfig().getString("reload_permission"));
                    p.sendMessage(ChatUtil.fixColor(msg));
                }
                return true;
            }
            if (!p.hasPermission(plugin.getConfig().getString("ping_permission"))) {
                String msg = plugin.getConfig().getString("use_denied");
                assert msg != null;
                msg = msg.replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"));
                msg = msg.replaceAll("%max_ping%", String.valueOf(plugin.getConfig().getInt("max_ping")));
                msg = msg.replaceAll("%permission_use%", plugin.getConfig().getString("ping_permission"));
                p.sendMessage(ChatUtil.fixColor(msg));
            } else {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    String msg = plugin.getConfig().getString("player_ping_cmd");
                    assert msg != null;
                    msg = msg.replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"));
                    msg = msg.replaceAll("%ping%", String.valueOf(target.getPing()));
                    msg = msg.replaceAll("%max_ping%", String.valueOf(plugin.getConfig().getInt("max_ping")));
                    msg = msg.replaceAll("%player%", target.getName());
                    msg = msg.replaceAll("%permission_use%", plugin.getConfig().getString("ping_permission"));
                    msg = msg.replaceAll("%permission_reload%", plugin.getConfig().getString("reload_permission"));
                    p.sendMessage(ChatUtil.fixColor(msg));
                } else {
                    String msg = plugin.getConfig().getString("player_offline");
                    assert msg != null;
                    msg = msg.replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"));
                    msg = msg.replaceAll("%max_ping%", String.valueOf(plugin.getConfig().getInt("max_ping")));
                    msg = msg.replaceAll("%permission_use%", plugin.getConfig().getString("ping_permission"));
                    msg = msg.replaceAll("%permission_reload%", plugin.getConfig().getString("reload_permission"));
                    p.sendMessage(ChatUtil.fixColor(msg));
                }
            }
        } else {
            String msg = plugin.getConfig().getString("unknown_cmd");
            assert msg != null;
            msg = msg.replaceAll("%prefix%", plugin.getConfig().getString("info_prefix"));
            msg = msg.replaceAll("%ping%", String.valueOf(p.getPing()));
            msg = msg.replaceAll("%max_ping%", String.valueOf(plugin.getConfig().getInt("max_ping")));
            msg = msg.replaceAll("%permission_use%", plugin.getConfig().getString("ping_permission"));
            msg = msg.replaceAll("%permission_reload%", plugin.getConfig().getString("reload_permission"));
            p.sendMessage(ChatUtil.fixColor(msg));
        }
        return false;
    }
}
