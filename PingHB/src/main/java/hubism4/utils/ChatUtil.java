package hubism4.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ChatUtil {
    public static String fixColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text.replace(">>", "»").replace("<<", "«"));
    }

    @SuppressWarnings("rawtypes")
    public static void sendTitleMessage(Player player, String text) {
        PacketPlayOutTitle subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, (IChatBaseComponent)IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}".replace("&", "§")), 100, 100, 100);
        (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)subtitle);
    }

    @SuppressWarnings("rawtypes")
    public static void sendSubTitleMessage(Player player, String text) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, (IChatBaseComponent)IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}".replace("&", "§")), 100, 100, 100);
        (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)title);
    }

    @SuppressWarnings("rawtypes")
    public static void sendActionBar(Player player, String text) {
        PacketPlayOutTitle actionbar = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, (IChatBaseComponent)IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}".replace("&", "§")), 0, 40, 0);
        (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)actionbar);
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static boolean sendMessage(CommandSender p, String text) {
        p.sendMessage(fixColor(text));
        return true;
    }

    public static boolean sendMessage(Player p, String text) {
        p.sendMessage(fixColor(text));
        return true;
    }

    public static void giveItems(Player p, ItemStack... items) {
        PlayerInventory playerInventory = p.getInventory();
        HashMap<Integer, ItemStack> notStored = playerInventory.addItem(items);
        for (Map.Entry<Integer, ItemStack> e : notStored.entrySet())
            p.getWorld().dropItemNaturally(p.getLocation(), e.getValue());
    }

    public static boolean isInteger(String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }

    public static Location locFromString(String str) {
        String[] str2loc = str.split(":");
        Location loc = new Location(Bukkit.getWorlds().get(0), 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        loc.setX(Double.parseDouble(str2loc[0]));
        loc.setY(Double.parseDouble(str2loc[1]));
        loc.setZ(Double.parseDouble(str2loc[2]));
        loc.setYaw(Float.parseFloat(str2loc[3]));
        loc.setPitch(Float.parseFloat(str2loc[4]));
        return loc;
    }

    public static String locToString(double x, double y, double z) {
        return String.valueOf(x) + ":" + y + ":" + z + ":" + 0.0F + ":" + 0.0F;
    }

    public static double round(double value, int decimals) {
        double p = Math.pow(10.0D, decimals);
        return Math.round(value * p) / p;
    }
}
