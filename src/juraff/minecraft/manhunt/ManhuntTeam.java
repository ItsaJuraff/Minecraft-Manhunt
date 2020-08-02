package juraff.minecraft.manhunt;

import java.util.Vector;
import java.lang.String;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ManhuntTeam {
	private String name;
	private Team team;
	private Score memberScore;
	private GameMode gamemode;
	private Vector<Player> players;

	
	/**
	 * creates team with name, scoreboard objectives, and default gamemode
	 * 
	 * @param name name of team
	 * @param obj objective to display
	 * @param gamemode desired gamemode
	 * */
	public ManhuntTeam(String name, Objective obj, GameMode gamemode) {
		this.players = new Vector<Player>();
		this.name = name;
		this.gamemode = gamemode;
		
		// create new team
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		
		try {
			this.team = board.registerNewTeam(this.name);
		} catch (IllegalArgumentException e) {
			this.team = board.getTeam(this.name);
		}
		
		// create score
		this.memberScore = obj.getScore(this.name);
	}
	
	/**
	 * name getter
	 * 
	 * @return name of team
	 * @see String
	 * */
	public String getName() { return this.name; }
	
	/**
	 * name setter 
	 * 
	 * @param name name of team
	 * */
	public void setName(String name) { this.name = name; }
	
	/**
	 * gets the vector of players
	 * 
	 * @return vector of players
	 * @see Player
	 * */
	public Vector<Player> getPlayers() {
		return players;
	}
	
	/** updates scoreboard for number of players */
	private void updateScoreboard() {
		this.memberScore.setScore(this.getNumPlayers());
	}
	
	/**
	 * gets the number of players on the team
	 * 
	 * @return number of players
	 * @see int
	 * */
	public int getNumPlayers() {
		return players.size();
	}
	
	/**
	 * adds player to Team
	 * 
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
	 * 
	 * @param player player object to remove
	 * */
	public void removePlayer(Player player) {
		this.team.removeEntry(player.getName());
		this.players.remove(player);
		this.updateScoreboard();
	}
	
	/**
	 * gets the player at an index
	 * 
	 * @param index index of player vector to get
	 * @return player at index
	 * @see Player
	 * */
	public Player getPlayer(int index) {
		return this.players.get(index);
	}
	
	/**
	 * checks if player is on team, returns index of player if found, -1 if not found
	 * 
	 * @param Player player to get index of
	 * @return index of player
	 * @see int
	 * */
	public int getPlayerIndex(Player player) {
		return this.players.indexOf(player);
	}
	
	/**
	 * checks if player exists on the team
	 * 
	 * @param player player to check for
	 * @return check if player is on team
	 * @see boolean
	 * */
	public boolean checkPlayer(Player player) {
		return this.players.contains(player);
	}
}