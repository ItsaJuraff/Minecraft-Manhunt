package juraff.minecraft.manhunt;

import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;


public class ManhuntGame {
	/** default team when player joins server */
	private int defaultJoinTeam = 0;
	/** default team when player leaves a team */
	private int defaultLeaveTeam = 2;
	/** vector to stores teams in */
	private Vector<ManhuntTeam> teams;
	
	/** holds return codes for functions where applicable */
	public boolean rc;
	
	
	/** default constructor */
	public ManhuntGame() {
		// create scoreboard
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		// create new objective if does not exist
		Objective obj;
		try {
			obj = board.registerNewObjective("TeamCount", "dummy", "Teams");
		} catch (IllegalArgumentException e) {
			obj = board.getObjective("TeamCount");
		}
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		// register teams
		this.teams = new Vector<ManhuntTeam>();
		for (ManhuntTeamName tname : ManhuntTeamName.values()) {
			this.teams.add(new ManhuntTeam(tname.name(), obj, tname.getGamemode()));
		}
	}
	
	/** 
	 * constructor with different defaultJoinTeam
	 * 
	 * @param defaultJoinTeam team a player joins by default when logging into a server
	 * */
	public ManhuntGame(int defaultJoinTeam) {
		this();
		this.defaultJoinTeam = defaultJoinTeam;
	}
	
	/**
	 * constructor with different defaultJoinTeam, defaultLeaveTeam
	 * @param defaultJoinTeam team index a player joins by default when logging into a server
	 * @param defaultLeaveTeam team index a player joins when leaving their current team*/
	public ManhuntGame(int defaultJoinTeam, int defaultLeaveTeam) {
		this();
		this.defaultJoinTeam = defaultJoinTeam;
		this.defaultLeaveTeam = defaultLeaveTeam;
	}
	
	
	/** getter for team */
	public Vector<ManhuntTeam> getTeams() { return this.teams; }
	
	/**
	 * adds player to default team 
	 * 
	 * @param player
	 * */
	public void joinTeam(Player player) {
		this.joinTeam(player, defaultJoinTeam);
	}
	
	/**
	 * adds player to team
	 * 
	 * @param index index of team to join*/
	public void joinTeam(Player player, int index) {
		// remove player
		for (ManhuntTeam t : teams) {
			t.removePlayer(player);
		}
		// add player to new team
		teams.get(index).addPlayer(player);
	}
	
	/**
	 * removes player from current team
	 * 
	 * @param player
	 * */
	public void leaveTeam(Player player) {
		int index;
		index = this.getTeam(player);
		
		// remove player
		if (index > -1) {
			teams.get(index).removePlayer(player);
		}
		
		// add player to default team
		this.joinTeam(player, defaultLeaveTeam);
	}
	
	/**
	 * gets the index of the team that player is current a part of
	 * 
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
