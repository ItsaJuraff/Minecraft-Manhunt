package juraff.minecraft.manhunt;

import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;


public class ManhuntGame {
	/** flag when game has started */
	private boolean startFlag = false;
	/** default team when player joins server */
	private int defaultJoinTeam = 0;
	/** default team when player leaves a team */
	private int defaultLeaveTeam = 2;
	/** vector to stores teams in */
	private Vector<ManhuntTeam> teams;
	
	
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
	
	/**
	 * checks if game has started
	 * 
	 * @return if game started
	 */
	public boolean hasStarted() {
		return this.startFlag;
	}
	
	/** starts the manhunt game */
	public void start() {
		// TODO add checks to see if there are players on teams
		this.startFlag = true;
	}
	
	public void stop() {
		this.startFlag = false;
	}
	
	/**
	 * getter for team
	 * 
	 * @return Vector of teams*/
	public Vector<ManhuntTeam> getTeams() {
		return this.teams;
	}
	
	/**
	 * adds player to default team 
	 * 
	 * @param player
	 * */
	public ManhuntTeam joinDefaultTeam(Player player) {
		ManhuntTeam team;
		team = this.getTeamByIndex(defaultJoinTeam);
		team.addPlayer(player);
		return team;
	}
	
	/**
	 * gets a team based on its name
	 * 
	 * @param index
	 * @return
	 */
	public ManhuntTeam getTeamByIndex(int index) {
		return this.teams.get(index);
	}
	
	
	/**
	 * gets a team based on its name
	 * 
	 * @param name team name
	 * @return
	 */
	public ManhuntTeam getTeamByName(String name) {
		ManhuntTeam team = null;
		for (ManhuntTeam t : teams) {
			if (t.getName().equalsIgnoreCase(name)) {
				team = t;
			}
		}
		return team;
	}
	
	/**
	 * adds a player to the team object, handles removing the player from their
	 * previous team
	 * 
	 * the previous team is returned since you are passing the new team into the function
	 * so most likely you will also have their current team
	 * 
	 * @param player 
	 * @param team
	 * @return previous team of player
	 */
	public ManhuntTeam joinTeam(Player player, ManhuntTeam team) {
		// removes player from current team
		ManhuntTeam currTeam;
		currTeam = this.getPlayerTeam(player);
		currTeam.removePlayer(player);
		
		// add player to new team
		team.addPlayer(player);
		
		return team;
	}
	
	/**
	 * removes player from their current team and assigns player to defaultLeaveTeam
	 * 
	 * @param player
	 * @return team player has joined
	 * */
	public ManhuntTeam leaveTeam(Player player) {
		// get default team
		ManhuntTeam newTeam;
		newTeam = this.getTeamByIndex(defaultLeaveTeam);
		
		// join new team
		newTeam = this.joinTeam(player, newTeam);
		
		return newTeam;
	}
	
	/**
	 * gets team that the player is currently on
	 * 
	 * @param player
	 * @return ManhuntTeam null if no team if found
	 */
	public ManhuntTeam getPlayerTeam(Player player) {
		ManhuntTeam team = null;
		for (ManhuntTeam t : this.teams) {
			if (t.checkPlayer(player)) {
				team = t;
				break;
			}
		}
		return team;
	}
	
	/**
	 * gets the index of the team that player is current a part of
	 * 
	 * @return index of team, values of -1 means player is not a part of a team
	 * */
	public int getPlayerTeamIndex(Player player) {
		int team_idx = -1;
		for (int i = 0; i < this.teams.size(); i++) {
			if (teams.get(i).checkPlayer(player)) {
				team_idx = i;
			}
		}
		return team_idx;
	}
}
