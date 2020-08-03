package juraff.minecraft.manhunt;

import java.util.Vector;
import java.lang.String;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public enum ManhuntTeam {
	Hunters(0, "Hunters", GameMode.SURVIVAL),
	Speedrunners(1, "Speedrunners", GameMode.SURVIVAL),
	Spectators(2, "Spectators", GameMode.SPECTATOR);
	
	
	/** gamemode for the team */
	public GameMode gamemode;
	/** index associated with each gamemode */
	public int index;
	
	/** name representing ManhuntTeam */
	private String  name;
	/** minecraft Team */
	private Team team;
	/** minecraft Score for players on each team */
	private Score memberScore;
	/** Vector of player on the team */
	private Vector<Player> players;
	
	
	/**
	 * default constructor for ManhuntTeam, default gamemode is survival
	 * @param name name of team
	 * @param obj objective to display current team count
	 * */
	private ManhuntTeam(int index, String name, GameMode gamemode) {
		// new vector for players
		this.players = new Vector<Player>();
		// take parameters
		this.name = name;
		this.gamemode = gamemode;
		this.index = index;
		
		// get main scoreboard
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		
		// get or create scoreboard
		Objective obj;
		try {
			obj = board.registerNewObjective("TeamCount", "dummy", "Teams");
		} catch (IllegalArgumentException e) {
			obj = board.getObjective("TeamCount");
		}
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		// get or create team
		try {
			this.team = board.registerNewTeam(this.name);
		} catch (IllegalArgumentException e) {
			this.team = board.getTeam(this.name);
		}
		
		// create score
		this.memberScore = obj.getScore(this.name);
		this.updateScoreboard();
	}
	
	
	/**
	 * name getter
	 * @return name
	 * */
	public String getName() { return this.name; }
	
	/**
	 * name setter 
	 * @param name desired name
	 * */
	public void setName(String name) { this.name = name; }
	
	/**
	 * gets the vector of players
	 * @return vector of players
	 * */
	public Vector<Player> getAllPlayers() {
		return players;
	}
	
	/** updates scoreboard for number of players */
	private void updateScoreboard() {
		this.memberScore.setScore(this.getNumPlayers());
	}
	
	/**
	 * gets the number of players on the team
	 * @return number of players
	 * */
	public int getNumPlayers() {
		return players.size();
	}
	
	/**
	 * adds player to Team
	 * @param player player object to add
	 * */
	public void addPlayer(Player player) {
		this.team.addEntry(player.getName());
		this.players.add(player);
		this.updateScoreboard();
		player.setGameMode(this.gamemode);
	}
	
	/**
	 * removes player from Team
	 * @param player player object to remove
	 * */
	public void removePlayer(Player player) {
		this.team.removeEntry(player.getName());
		this.players.remove(player);
		this.updateScoreboard();
	}
	
	/**
	 * gets the player at an index
	 * @param index index of player vector to get
	 * @return player at index
	 * */
	public Player getPlayer(int index) {
		return this.players.get(index);
	}
	
	/**
	 * checks if player is on team, returns index of player if found, -1 if not found
	 * @param player player to get index of
	 * @return index of player
	 * */
	public int getPlayerIndex(Player player) {
		return this.players.indexOf(player);
	}
	
	/**
	 * checks if player exists on the team
	 * @param player player to check for
	 * @return boolean if player is on team
	 * */
	public boolean checkPlayer(Player player) {
		return this.players.contains(player);
	}
}
