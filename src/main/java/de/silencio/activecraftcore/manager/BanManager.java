package de.silencio.activecraftcore.manager;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.PlayerBanEvent;
import de.silencio.activecraftcore.events.PlayerIpBanEvent;
import de.silencio.activecraftcore.events.PlayerIpUnbanEvent;
import de.silencio.activecraftcore.events.PlayerUnbanEvent;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.StringUtils;
import de.silencio.activecraftcore.utils.TimeUtils;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Date;

public class BanManager {
    public static class IP {
        public static boolean isBanned(String target) {
            for (BanEntry banEntry : Bukkit.getBanList(BanList.Type.IP).getBanEntries()) {
                if (target.equals(banEntry.getTarget())) return true;
            }
            return false;
        }

        public static void ban(String target, String reason, Date expires, String source) {
            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), () -> {
                PlayerIpBanEvent event = new PlayerIpBanEvent(target, reason, expires, source);
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    Bukkit.getBanList(BanList.Type.IP).addBan(target, reason, expires, source);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getAddress().getAddress().toString().replace("/", "").equals(target)) {
                            Profile profile = ActiveCraftCore.getProfile(player);
                            profile.set(Profile.Value.IP_BANS, profile.getIpBans() + 1);
                            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), () -> {
                                String expirationString =
                                        TimeUtils.getRemainingAsString(expires).equalsIgnoreCase("never") ?
                                                CommandMessages.BAN_EXPIRATION_PERMANENT() : CommandMessages.BAN_EXPIRATION(TimeUtils.getRemainingAsString(expires));
                                player.kickPlayer(CommandMessages.BAN_TITLE()
                                        + "\n \n" + expirationString
                                        + "\n" + CommandMessages.BAN_REASON(reason));
                            });
                        }
                    }
                }
            });
        }

        public static void ban(Player target, String reason, Date expires, String source) {
            ban(target.getName(), reason, expires, source);
        }

        public static void unban(String target) {
            if (!StringUtils.isValidInet4Address(target)) {
                return;
            }
            PlayerIpUnbanEvent event = new PlayerIpUnbanEvent(target);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
            Bukkit.getBanList(BanList.Type.IP).pardon(target);
        }

        public static void unban(Player target) {
            unban(target.getName());
        }

        public static java.util.Set<org.bukkit.BanEntry> getBans() {
            return Bukkit.getBanList(BanList.Type.IP).getBanEntries();
        }

        public static BanEntry getBanEntry(String target) {
            return Bukkit.getBanList(BanList.Type.IP).getBanEntry(target);
        }

        public static BanEntry getBanEntry(Player target) {
            return Bukkit.getBanList(BanList.Type.IP).getBanEntry(target.getName());
        }
    }

    public static class Name {
        public static boolean isBanned(String target) {
            return Bukkit.getBanList(BanList.Type.NAME).isBanned(target);
        }

        public static void ban(String target, String reason, Date expires, String source) {
            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), () -> {
                PlayerBanEvent event = new PlayerBanEvent(target, reason, expires, source);
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    Bukkit.getBanList(BanList.Type.NAME).addBan(target, reason, expires, source);
                    Profile profile = ActiveCraftCore.getProfile(target);
                    profile.set(Profile.Value.BANS, profile.getBans() + 1);
                    if (Bukkit.getPlayer(target) != null)
                        Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), () -> {
                            String expirationString =
                                    TimeUtils.getRemainingAsString(expires).equalsIgnoreCase("never") ?
                                            CommandMessages.BAN_EXPIRATION_PERMANENT() : CommandMessages.BAN_EXPIRATION(TimeUtils.getRemainingAsString(expires));
                            Bukkit.getPlayer(target).kickPlayer(CommandMessages.BAN_TITLE()
                                    + "\n \n" + expirationString
                                    + "\n" + CommandMessages.BAN_REASON(reason));
                        });
                }
            });
        }

        public static void ban(Player target, String reason, Date expires, String source) {
            ban(target.getName(), reason, expires, source);
        }

        public static void unban(String target) {
            PlayerUnbanEvent event = new PlayerUnbanEvent(target);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
            Bukkit.getBanList(BanList.Type.NAME).pardon(target);
        }

        public static void unban(Player target) {
            unban(target.getName());
        }

        public static java.util.Set<org.bukkit.BanEntry> getBans() {
            return Bukkit.getBanList(BanList.Type.NAME).getBanEntries();
        }

        public static BanEntry getBanEntry(String target) {
            return Bukkit.getBanList(BanList.Type.NAME).getBanEntry(target);
        }

        public static BanEntry getBanEntry(Player target) {
            return Bukkit.getBanList(BanList.Type.NAME).getBanEntry(target.getName());
        }
    }
}