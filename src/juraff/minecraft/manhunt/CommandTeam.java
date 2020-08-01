package juraff.minecraft.manhunt;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTeam implements CommandExecutor {
	/** stores team instance */
	private Team team;
	
	/** constructor CommandTeam */
	public CommandTeam(Team team) {
		this.team = team;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		if (args.length == 0) {
			// show help message
			sender.sendMessage("Please provide at least one argument");
			return false;
		} else if (args[0].equalsIgnoreCase("join")) {
			// add player to team
			sender.sendMessage("joined team");
			return this.team.addPlayer(player);
		} else if (args[0].equalsIgnoreCase("leave")) {
			// remove player from team
			sender.sendMessage("left team");
			return this.team.removePlayer(player);
		}
		return true;
	}
}
