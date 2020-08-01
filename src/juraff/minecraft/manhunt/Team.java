package juraff.minecraft.manhunt;

import java.util.Vector;
import java.lang.String;

import org.bukkit.entity.Player;

public class Team {
	private String name;
	private Vector<Player> players;

	/** creates empty team */
	public Team() {
		this.players = new Vector<Player>();
	}
	
	/** creates team with name */
	public Team(String name) {
		this.name = name;
		this.players = new Vector<Player>();
	}
	
	/** name getter */
	public String getName() { return this.name; }
	/** name setter */
	public void setName(String name) { this.name = name; }
	
	/** gets the vector of players */
	public Vector<Player> getPlayers() {
		return players;
	}
	
	/** gets the number of players on the team */
	public int getNumPlayers() {
		return players.size();
	}
	
	/** adds player to Team */
	public boolean addPlayer(Player player) {
		return this.players.add(player);
	}
	
	/** removes player from Team */
	public boolean removePlayer(Player player) {
		return this.players.remove(player);
	}
	
	/** gets the player at an index */
	public Player getPlayer(int index) {
		return this.players.get(index);
	}
	
	/** checks if player is on team, returns index of player if found, -1 if not found */
	public int getPlayerIndex(Player player) {
		return this.players.indexOf(player);
	}
	
	/** checks if player exists on the team */
	public boolean checkPlayer(Player player) {
		return this.players.contains(player);
	}
}
