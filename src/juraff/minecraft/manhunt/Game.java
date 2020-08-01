package juraff.minecraft.manhunt;

import java.util.Vector;

import org.bukkit.entity.Player;

public class Game {
	private String[] names = {"Speedrunners", "Hunters", "Spectators"};
	private Vector<Team> teams;
	
	/** default constructor */
	public Game() {
		this.teams = new Vector<Team>();
		this.addTeams(this.names);

	}
	
	/** constructor custom names */
	public Game(String[] names) {
		this.names = names;
		this.addTeams(this.names);
	}
	
	
	/** creates a new team vector with names */
	private void addTeams(String[] names) {
		this.teams = new Vector<Team>();
		for (int i = 0; i < this.names.length; i++) {
			this.teams.add(new Team(this.names[i]));
		}
	}
	
	
	/** getter for team */
	public Vector<Team> getTeams() { return this.teams; }
	
	
	/** adds player to team */
	public boolean joinTeam(Player player, int team) {
		// TODO add checks if player is already a part of a team
		return teams.get(team).addPlayer(player);
	}
	
	
	/** removes player from current team */
	public boolean leaveTeam(Player player) {
		int index;
		index = this.getTeam(player);
		if (index > -1) {
			return teams.get(index).removePlayer(player);
		} else {
			return false;
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
