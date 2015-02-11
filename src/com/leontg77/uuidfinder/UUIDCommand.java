package com.leontg77.uuidfinder;

import static com.leontg77.uuidfinder.UUIDFinder.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UUIDCommand implements CommandExecutor {
	
	private boolean isUUID(String check) {
		return check.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}");
	}
	
	public boolean onCommand(CommandSender player, Command cmd, String label, String[] args) {	
		if (cmd.getName().equalsIgnoreCase("uuid")) {
			if (player.hasPermission("uuid.find")) {
				if (args.length == 0) {
					player.sendMessage(ChatColor.RED + "Usage: /uuid <name|uuid|info|reload>");
					return true;
				}
			
				if (args[0].equalsIgnoreCase("info")) {
					if (player.hasPermission("uuid.info")) {
						player.sendMessage(UUIDFinder.prefix() + "This is a plugin by LeonTG77 made to find the uuid of of a name or the name of the uuid.");
						player.sendMessage(UUIDFinder.prefix() + "Current version: v" + plugin.getDescription().getVersion());
					} else {
						player.sendMessage(UUIDFinder.noPermMsg());
					}
					return true;
				}
				
				if (args[0].equalsIgnoreCase("reload")) {
					if (player.hasPermission("uuid.reload")) {
						player.sendMessage(UUIDFinder.prefix() + "Successfully reloaded the settings.");
						plugin.reloadConfig();
					} else {
						player.sendMessage(UUIDFinder.noPermMsg());
					}
					return true;
				}
				
				if (isUUID(args[0])) {
					player.sendMessage(UUIDFinder.prefix() + "The name(s) of given uuid is: " + UUIDFinder.getName(args[0]));
				} else {
					
				}
			} else {
				player.sendMessage(UUIDFinder.noPermMsg());
			}
		}
		return true;
	}
}