package juraff.minecraft.manhunt;

import java.util.Vector;

import org.bukkit.entity.Player;

public class Game {
	/** team names */
	private String[] names = {"Hunters", "Speedrunners", "Spectators"};
	/** default team when player joins server */
	private int defaultJoinTeam = 0;
	/** default team when player leaves a team */
	private int defaultLeaveTeam = 2;
	/** vector to stores teams in */
	private Vector<Team> teams;
	
	/** holds return codes for functions where applicable */
	public boolean rc;
	
	
	/** default constructor */
	public Game() {
		this.teams = new Vector<Team>();
		for (int i = 0; i < this.names.length; i++) {
			this.teams.add(new Team(this.names[i]));
		}
	}
	
	/** 
	 * constructor with different defaultJoinTeam
	 * @param defaultJoinTeam team a player joins by default when logging into a server
	 * */
	public Game(int defaultJoinTeam) {
		this();
		this.defaultJoinTeam = defaultJoinTeam;
	}
	
	/**
	 * constructor with different defaultJoinTeam, defaultLeaveTeam
	 * @param defaultJoinTeam team index a player joins by default when logging into a server
	 * @param defaultLeaveTeam team index a player joins when leaving their current team*/
	public Game(int defaultJoinTeam, int defaultLeaveTeam) {
		this();
		this.defaultJoinTeam = defaultJoinTeam;
		this.defaultLeaveTeam = defaultLeaveTeam;
	}
	
	
	/** getter for team */
	public Vector<Team> getTeams() { return this.teams; }
	
	/** adds player to default team */
	public boolean joinTeam(Player player) {
		return this.joinTeam(player, defaultJoinTeam);
	}
	
	/** adds player to team */
	public boolean joinTeam(Player player, int team) {
		// remove player
		for (Team t : teams) {
			t.removePlayer(player);
		}
		// add player to new team
		return teams.get(team).addPlayer(player);
	}
	
	/** removes player from current team */
	public boolean leaveTeam(Player player) {
		boolean rc = false;
		int index;
		index = this.getTeam(player);
		
		// remove player
		if (index > -1) {
			rc = teams.get(index).removePlayer(player);
		}
		
		// add player to default team
		if (rc) {
			rc = this.joinTeam(player, defaultLeaveTeam);
		}
		
		return rc;
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
