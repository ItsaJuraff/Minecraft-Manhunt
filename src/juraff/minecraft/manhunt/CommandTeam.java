package juraff.minecraft.manhunt;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTeam implements CommandExecutor {
	/** stores team instance */
	private Game game;
	
	/** constructor CommandTeam */
	public CommandTeam(Game game) {
		this.game = game;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		if (args.length == 0) {
			// show help message
			sender.sendMessage("Please provide at least one argument");
			return false;
		} else if (args[0].equalsIgnoreCase("join")) {
			// TODO add parsing of strings for teams
			
			// get desired team
			int team = Integer.parseInt(args[1]);
			sender.sendMessage("joined team");
			// add player to team
			return this.game.joinTeam(player, team);
		} else if (args[0].equalsIgnoreCase("leave")) {
			// remove player from team
			sender.sendMessage("left team");
			return this.game.leaveTeam(player);
		}
		return true;
	}
}
