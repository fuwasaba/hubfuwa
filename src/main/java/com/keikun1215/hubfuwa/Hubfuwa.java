package com.keikun1215.hubfuwa;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class hubfuwa extends JavaPlugin implements Listener, TabCompleter {
  public void onEnable() {
    getCommand("hub").setTabCompleter(this);
    Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin)this);
  }
  
  public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    if (cmd.getName().equalsIgnoreCase("hub") && args.length == 1) {
      List<String> res = new ArrayList<>();
      res.add("gui");
      res.add("info");
      return res;
    } 
    return null;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (label.equalsIgnoreCase("hub") || label.equalsIgnoreCase("hubfuwa:hub")) {
	        Player player = (Player)sender;

	        if (args.length == 0) {
	            World world = Bukkit.getWorld("earth_1-750_mod-compatible_with-features_1-19-3");
	            player.teleport(new Location(world, -5792.D, 84.0D, -3751.0D));
	            return true;
      } 
      if (args[0].equalsIgnoreCase("gui")) {
        Inventory hubgui = Bukkit.createInventory(null, 36, "§dfuwasaba HUB GUI");
        ItemStack backarrow = new ItemStack(Material.ARROW);
        ItemMeta backarrowmeta = backarrow.getItemMeta();
        backarrowmeta.setDisplayName("HUBに戻る");
        backarrow.setItemMeta(backarrowmeta);
        hubgui.setItem(0, backarrow);
        ItemStack NY = new ItemStack(Material.COPPER_BLOCK);
        ItemMeta NYM = NY.getItemMeta();
        NYM.setDisplayName("ニューヨーク");
        NY.setItemMeta(NYM);
        hubgui.setItem(11, NY);
        ItemStack BR = new ItemStack(Material.IRON_BLOCK);
        ItemMeta BRM = BR.getItemMeta();
        BRM.setDisplayName("ベルリン");
        BR.setItemMeta(BRM);
        hubgui.setItem(12, BR);
        ItemStack TK = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta TKM = TK.getItemMeta();
        TKM.setDisplayName("東京");
        TK.setItemMeta(TKM);
        hubgui.setItem(13, TK);
        ItemStack SH = new ItemStack(Material.GOLD_ORE);
        ItemMeta SHM = SH.getItemMeta();
        SHM.setDisplayName("上海");
        SH.setItemMeta(SHM);
        hubgui.setItem(14, SH);
        ItemStack KR = new ItemStack(Material.SANDSTONE);
        ItemMeta KRM = KR.getItemMeta();
        KRM.setDisplayName("カイロ");
        KR.setItemMeta(KRM);
        hubgui.setItem(15, KR);
        ItemStack MC = new ItemStack(Material.SNOW_BLOCK);
        ItemMeta MCM = MC.getItemMeta();
        MCM.setDisplayName("モスクワ");
        MC.setItemMeta(MCM);
        hubgui.setItem(22, MC);
        player.openInventory(hubgui);
        player.addScoreboardTag("hubguiopening");
      } else if (args[0].equalsIgnoreCase("info")) {
        player.sendMessage("§m                            ");
        player.sendMessage("§dHUB plugin(ver2.1.1)");
        player.sendMessage("Changed:");
        player.sendMessage("§aHUBtpのYを修正");
        player.sendMessage("§4かちゅーしゃによる介入があったようだ...");
        player.sendMessage("Commands:");
        player.sendMessage("/hub - HUBにテレポートする。");
        player.sendMessage("| gui - 各都市やHUBへtpできるGUIを開く。");
        player.sendMessage("| info - このメッセージを表示する。意味があるかは不明。");
        player.sendMessage("§m                            ");
      } else {
        player.sendMessage("§cInvalid arugment(0).\nUsage: /hub [gui|info]");
      } 
    } 
    return true;
  }
  
  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event) {
    HumanEntity player = event.getPlayer();
    if (player.getScoreboardTags().contains("hubguiopening"))
      player.removeScoreboardTag("hubguiopening"); 
  }
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    WorldCreator worldCreator = new WorldCreator("earth_1-750_mod-compatible_with-features_1-19-3");
    Bukkit.createWorld(worldCreator);
    HumanEntity player = event.getWhoClicked();
    if (player.getScoreboardTags().contains("hubguiopening")) {
      if (event.getCurrentItem().getType() == Material.COPPER_BLOCK) {
        player.teleport(new Location(Bukkit.getWorld("earth_1-750_mod-compatible_with-features_1-19-3"), -10123.0D, 63.0D, -5566.0D));
        player.sendMessage("ニューヨークにテレポートしました。");
      } else if (event.getCurrentItem().getType() == Material.IRON_BLOCK) {
        player.teleport(new Location(Bukkit.getWorld("earth_1-750_mod-compatible_with-features_1-19-3"), 1833.0D, 67.0D, -7006.0D));
        player.sendMessage("ベルリンにテレポートしました。");
      } else if (event.getCurrentItem().getType() == Material.GRASS_BLOCK) {
        player.teleport(new Location(Bukkit.getWorld("earth_1-750_mod-compatible_with-features_1-19-3"), 19086.0D, 63.0D, -4885.0D));
        player.sendMessage("東京にテレポートしました。");
      } else if (event.getCurrentItem().getType() == Material.GOLD_ORE) {
        player.teleport(new Location(Bukkit.getWorld("earth_1-750_mod-compatible_with-features_1-19-3"), 16604.0D, 63.0D, -4251.0D));
        player.sendMessage("上海にテレポートしました。");
      } else if (event.getCurrentItem().getType() == Material.SANDSTONE) {
        player.teleport(new Location(Bukkit.getWorld("earth_1-750_mod-compatible_with-features_1-19-3"), 4268.0D, 63.0D, -4137.0D));
        player.sendMessage("カイロにテレポートしました。");
      } else if (event.getCurrentItem().getType() == Material.SNOW_BLOCK) {
        player.teleport(new Location(Bukkit.getWorld("earth_1-750_mod-compatible_with-features_1-19-3"), 5185.0D, 67.0D, -7596.0D));
        player.sendMessage("モスクワにテレポートしました。");
      } else if (event.getCurrentItem().getType() == Material.ARROW) {
        player.teleport(new Location(Bukkit.getWorld("earth_1-750_mod-compatible_with-features_1-19-3"), -5792.D, 84.0D, -3751.0D));
        player.sendMessage("HUBにテレポートしました。");
      } 
      event.setCancelled(true);
    } 
  }
}
