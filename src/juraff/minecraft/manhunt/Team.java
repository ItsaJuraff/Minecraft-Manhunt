package juraff.minecraft.manhunt;

import java.util.Vector;

import org.bukkit.entity.Player;

public class Team {
	private Vector<Player> players;

	public Team() {
		this.players = new Vector<Player>();
	}
	
	public Team(Vector<Player> players) {
		this.players = (Vector<Player>) players.clone();
	}
	
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

}
