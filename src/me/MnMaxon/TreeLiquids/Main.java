package me.MnMaxon.TreeLiquids;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	public static String dataFolder;
	public static Main plugin;
	public static SuperYaml MainConfig;

	@Override
	public void onEnable() {
		plugin = this;
		dataFolder = this.getDataFolder().getAbsolutePath();
		reloadConfigs();
		getServer().getPluginManager().registerEvents(new MainListener(), this);
	}

	public static void reloadConfigs() {
		MainConfig = new SuperYaml(dataFolder + "/Config.yml");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player) || ((Player) sender).hasPermission("TreeLiquids.Reload")) {
			reloadConfigs();
			sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
		} else
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
		return true;
	}
}