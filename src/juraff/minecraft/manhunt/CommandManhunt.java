package juraff.minecraft.manhunt;

import java.util.Vector;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManhunt implements CommandExecutor {
	/** stores team instance */
	private Game game;
	
	/** constructor CommandTeam */
	public CommandManhunt(Game game) {
		this.game = game;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean rc = false;
		Player player = (Player) sender;
		
		if (args.length == 0) {
			// show help message
			sender.sendMessage("Please provide at least one argument");
			return false;
		} else if (args[0].equalsIgnoreCase("list")) {
			// prints current count of players for each team
			Vector<Team> teams = game.getTeams();
			for (Team team : teams) {
				String output = String.format("%s: %d", team.getName(), team.getNumPlayers());
				sender.sendMessage(output);
			}
		} else if (args[0].equalsIgnoreCase("join")) {
			// get team index with input of string or int
			int team = 0;
			try {
				team = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				Vector<Team> teams = game.getTeams();
				for (int i = 0; i < teams.size(); i++) {
					if (args[1].equalsIgnoreCase(teams.get(i).getName())) {
						team = i;
					}
				}
			}
			// add player to team
			rc = this.game.joinTeam(player, team);
			if (rc) {
				String msg = String.format("Joined team %s", game.getTeams().get(team).getName());
				sender.sendMessage(msg);
			}
			return rc;
		} else if (args[0].equalsIgnoreCase("leave")) {
			// leave team
			rc = this.game.leaveTeam(player);
			if (rc) {
				sender.sendMessage("Left team");
			}
			return rc;
		}
		return true;
	}
}
