package com.keikun1215.hubfuwa;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public final class Hubfuwa extends JavaPlugin implements Listener, TabCompleter {
    @Override
    public void onEnable() {
        getCommand("hub").setTabCompleter(this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hub") && args.length == 1) {
            List<String> res = new ArrayList<String>();
            res.add("gui");
            res.add("info");
            return res;
        } else {
            return null;
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("hub") || label.equalsIgnoreCase("hubfuwa:hub")) {
            Player player = (Player)sender;
            WorldCreator worldCreator = new WorldCreator("world");
            Bukkit.createWorld(worldCreator);
            if (args.length == 0) {
                player.teleport(new Location(Bukkit.getWorld("world"), 0, 81, 0));
                return true;
            }
            if (args[0].equalsIgnoreCase("gui")) {
                Inventory hubgui = Bukkit.createInventory(null,36,"§dFuwasaba HUB GUI");
                //backToHub
                ItemStack backarrow = new ItemStack(Material.ARROW);
                ItemMeta backarrowmeta = backarrow.getItemMeta();
                backarrowmeta.setDisplayName("HUBに戻る");
                backarrow.setItemMeta(backarrowmeta);
                hubgui.setItem(0, backarrow);
                //!backToHub
                //NY
                ItemStack NY = new ItemStack(Material.COPPER_BLOCK);
                ItemMeta NYM = NY.getItemMeta();
                NYM.setDisplayName("ニューヨーク");
                NY.setItemMeta(NYM);
                hubgui.setItem(11, NY);
                //!NY
                //BR
                ItemStack BR = new ItemStack(Material.IRON_BLOCK);
                ItemMeta BRM = BR.getItemMeta();
                BRM.setDisplayName("ベルリン");
                BR.setItemMeta(BRM);
                hubgui.setItem(12, BR);
                //!BR
                //TK
                ItemStack TK = new ItemStack(Material.GRASS_BLOCK);
                ItemMeta TKM = BR.getItemMeta();
                TKM.setDisplayName("東京");
                TK.setItemMeta(TKM);
                hubgui.setItem(13, TK);
                //!TK
                //SH
                ItemStack SH = new ItemStack(Material.GOLD_ORE);
                ItemMeta SHM = SH.getItemMeta();
                SHM.setDisplayName("上海");
                SH.setItemMeta(SHM);
                hubgui.setItem(14, SH);
                //!SH
                //KR
                ItemStack KR = new ItemStack(Material.SANDSTONE);
                ItemMeta KRM = KR.getItemMeta();
                KRM.setDisplayName("カイロ");
                KR.setItemMeta(KRM);
                hubgui.setItem(15, KR);
                //!KR
                //MC
                ItemStack MC = new ItemStack(Material.SNOW_BLOCK);
                ItemMeta MCM = MC.getItemMeta();
                MCM.setDisplayName("モスクワ");
                MC.setItemMeta(MCM);
                hubgui.setItem(22, MC);
                //!KR
                player.openInventory(hubgui);
                player.addScoreboardTag("hubguiopening");
            } else if (args[0].equalsIgnoreCase("info")) {
                player.sendMessage("§m                             ");
                player.sendMessage("§dFuwasaba HUB plugin(ver2.0.0)");
                player.sendMessage("Changed:");
                player.sendMessage("+ §aGUI機能の追加(引数に\"gui\"と\"info\"を追加)");
                player.sendMessage("+ §a他ディメンションでhubコマンドを使用した際オーバーワールドに移動せず壁の中にテレポートするバグを修正");
                player.sendMessage("Commands:");
                player.sendMessage("/hub - §aHUB(0 81 0)にテレポートする。");
                player.sendMessage("| gui - §aHUBの機能を持つGUIを開く。");
                player.sendMessage("| info - §aこのメッセージを表示する。");
                player.sendMessage("§m                             ");
            } else {
                player.sendMessage("§cInvalid arugment(0).\nUsage: /hub [gui|info]");
            }
        }
        return true;
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        HumanEntity player = event.getPlayer();
        if (player.getScoreboardTags().contains("hubguiopening")) {
            player.removeScoreboardTag("hubguiopening");
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        WorldCreator worldCreator = new WorldCreator("world");
        Bukkit.createWorld(worldCreator);
        HumanEntity player = event.getWhoClicked();
        if (player.getScoreboardTags().contains("hubguiopening")) {
            if (event.getCurrentItem().getType() == Material.COPPER_BLOCK) {
                player.teleport(new Location(Bukkit.getWorld("world"), -10123, 63, -5566));
                player.sendMessage("ニューヨークにテレポートしました。");
            } else if (event.getCurrentItem().getType() == Material.IRON_BLOCK) {
                player.teleport(new Location(Bukkit.getWorld("world"), 1833, 67, -7006));
                player.sendMessage("ベルリンにテレポートしました。");
            } else if (event.getCurrentItem().getType() == Material.GRASS_BLOCK) {
                player.teleport(new Location(Bukkit.getWorld("world"), 19086, 63, -4885));
                player.sendMessage("東京にテレポートしました。");
            } else if (event.getCurrentItem().getType() == Material.GOLD_ORE) {
                player.teleport(new Location(Bukkit.getWorld("world"), 16604, 63, -4251));
                player.sendMessage("上海にテレポートしました。");
            } else if (event.getCurrentItem().getType() == Material.SANDSTONE) {
                player.teleport(new Location(Bukkit.getWorld("world"), 4268, 63, -4137));
                player.sendMessage("カイロにテレポートしました。");
            } else if (event.getCurrentItem().getType() == Material.SNOW_BLOCK) {
                player.teleport(new Location(Bukkit.getWorld("world"), 5185, 67, -7596));
                player.sendMessage("モスクワにテレポートしました。");
            } else if (event.getCurrentItem().getType() == Material.ARROW) {
                player.teleport(new Location(Bukkit.getWorld("world"), 0, 81, 0));
                player.sendMessage("HUBにテレポートしました。");
            }
            event.setCancelled(true);
        }
    }
}

