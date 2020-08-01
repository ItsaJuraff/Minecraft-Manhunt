package juraff.minecraft.manhunt;

import java.util.Vector;

import org.bukkit.entity.Player;

public class Game {
	private Vector<Team> teams;
	
	/** default constructor */
	public Game() {
		final String[] names = {"Speedrunners", "Hunters", "Spectators"};
		
		this.teams = new Vector<Team>();
		for (int i = 0; i < names.length; i++) {
			this.teams.add(new Team(names[i]));
		}
	}
	
	/** constructor custom names */
	public Game(String[] names) {
		this.teams = new Vector<Team>();
		for (int i = 0; i < names.length; i++) {
			this.teams.add(new Team(names[i]));
		}
	}
	
	/** adds player to team */
	public boolean joinTeam(Player player, int team) {
		// TODO add checks if player is already a part of a team
		return teams.get(team).addPlayer(player);
	}
	
	/** removes player from current team */
	public boolean leaveTeam(Player player) {
		int index;
		index = this.getTeam(player);
		if (index < -1) {
			return teams.get(index).removePlayer(player);
		} else {
			return true;
		}
	}
	
	/**
	 * gets the index of the team that player is current a part of
	 * @return index of team, values of -1 means player is not a part of a team
	 * */
	public int getTeam(Player player) {
		int team_idx = -1;
		for (int i = 0; i < this.teams.size(); i++) {
			if (teams.get(i).checkPlayer(player)) {
				team_idx = i;
			}
		}
		return team_idx;
	}
}
