package juraff.minecraft.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public class ManhuntGame {
	/** flag when game has started */
	private boolean startFlag = false;
	/** default team when player joins server */
	private ManhuntTeam defaultJoinTeam = ManhuntTeam.Hunters;
	/** default team when player leaves a team */
	private ManhuntTeam defaultLeaveTeam = ManhuntTeam.Spectators;
	
	/** default constructor */
	public ManhuntGame() {
		World overworld = Bukkit.getWorlds().get(0);
		overworld.setTime(6000);
		overworld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
	}
	
	/** 
	 * constructor with different defaultJoinTeam
	 * @param defaultJoinTeam team a player joins by default when logging into a server
	 * */
	public ManhuntGame(ManhuntTeam defaultJoinTeam) {
		this();
		this.defaultJoinTeam = defaultJoinTeam;
	}
	
	/**
	 * constructor with different defaultJoinTeam, defaultLeaveTeam
	 * @param defaultJoinTeam team index a player joins by default when logging into a server
	 * @param defaultLeaveTeam team index a player joins when leaving their current team
	 * */
	public ManhuntGame(ManhuntTeam defaultJoinTeam, ManhuntTeam defaultLeaveTeam) {
		this();
		this.defaultJoinTeam = defaultJoinTeam;
		this.defaultLeaveTeam = defaultLeaveTeam;
	}
	
	/**
	 * checks if game has started
	 * @return if game started
	 */
	public boolean hasStarted() {
		return this.startFlag;
	}
	
	/** starts the manhunt game */
	public void start() {
		this.startFlag = true;
		
		// hide scoreboard
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		board.clearSlot(DisplaySlot.SIDEBAR);
		
		// 
		
		for (ManhuntTeam team : ManhuntTeam.values()) {
			for (Player player : team.getAllPlayers()) {
				// set gamemode
				player.setGameMode(team.gamemode);
				// give hunters a compass
				if (team == ManhuntTeam.Hunters) {
					this.giveCompass(player);
				}
			}
		}
	}
	
	/** stops the manhunt game */
	public void stop() {
		this.startFlag = false;
		
		// set game rule
		World overworld = Bukkit.getWorlds().get(0);
		overworld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
	}
	
	/**
	 * adds player to default team 
	 * @param player
	 * */
	public void joinDefaultTeam(Player player) {
		defaultJoinTeam.addPlayer(player);
		return;
	}
	
	/**
	 * gives a player a compass
	 * @param player
	 */
	public void giveCompass(Player player) {
		ItemStack compass = new ItemStack(Material.COMPASS);
		player.getInventory().addItem(compass);
	}
	
	/**
	 * gets a team based on its name
	 * @param index
	 * @return
	 */
	public ManhuntTeam getTeamByIndex(int index) {
		ManhuntTeam team = null;
		for (ManhuntTeam t : ManhuntTeam.values()) {
			if (index == t.index) {
				team = t;
			}
		}
		return team;
	}
	
	/**
	 * gets a team based on its name
	 * @param name team name
	 * @return
	 */
	public ManhuntTeam getTeamByName(String name) {
		ManhuntTeam team = null;
		for (ManhuntTeam t : ManhuntTeam.values()) {
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
	 * @param player
	 * @return team player has joined
	 * */
	public void leaveTeam(Player player) {
		// join default leave team
		this.joinTeam(player, defaultLeaveTeam);
		return;
	}
	
	/**
	 * gets team that the player is currently on
	 * @param player
	 * @return ManhuntTeam null if no team if found
	 */
	public ManhuntTeam getPlayerTeam(Player player) {
		ManhuntTeam team = null;
		for (ManhuntTeam t : ManhuntTeam.values()) {
			if (t.checkPlayer(player)) {
				team = t;
				break;
			}
		}
		return team;
	}
	
	/**
	 * gets the index of the team that player is current a part of
	 * @return index of team, values of -1 means player is not a part of a team
	 * */
	public int getPlayerTeamIndex(Player player) {
		ManhuntTeam team = null;
		for (ManhuntTeam t : ManhuntTeam.values()) {
			if (t.checkPlayer(player)) {
				team = t;
			}
		}
		return team.index;
	}
}
