package juraff.minecraft.manhunt;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class CommandManhunt implements CommandExecutor {
	/** stores team instance */
	private ManhuntGame game;
	
	/** constructor CommandTeam */
	public CommandManhunt(ManhuntGame game) {
		this.game = game;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// cast sender to player
		Player player = (Player) sender;
		
		if (args.length == 0) {
			// show help message
			sender.sendMessage("Please provide at least one positional argument");
			return false;
		} else if (args[0].equalsIgnoreCase("compass")) {
			if (ManhuntTeam.Hunters.checkPlayer(player)) {
				game.giveCompass(player);
			}
		} else if (game.hasStarted()) {
			// ingame commands
			if (args[0].equalsIgnoreCase("stop")) {
				game.stop();
			}
		} else if (!game.hasStarted()) {
			if (args[0].equalsIgnoreCase("start")) {
				game.start();
			}
		} else {
			switch (args[0].toLowerCase()) {
			case "list":
				// prints current count of players for each team
				for (ManhuntTeam team : ManhuntTeam.values()) {
					String output = String.format("%d - %s: %d",team.index, team.getName(), team.getNumPlayers());
					sender.sendMessage(output);
				}
				return true;
			case "join":
				// get team index with input of string or int
				ManhuntTeam team = null;
				try {
					team = this.game.getTeamByIndex(Integer.parseInt(args[1]));
				} catch(NumberFormatException e) {
					team = this.game.getTeamByName(args[1]);
				}
				if (team != null) {
					// add player to team
					game.joinTeam(player, team);
					String msg = String.format("Joined team %s", team.getName());
					sender.sendMessage(msg);
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Team does not exist!");
					return false;
				}
			case "leave":
				// leave team
				this.game.leaveTeam(player);
				sender.sendMessage("Left team");
				return true;
			}
		}
		return true;
	}
}
