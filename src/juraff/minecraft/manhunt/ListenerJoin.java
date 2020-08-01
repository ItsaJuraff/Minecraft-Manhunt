package juraff.minecraft.manhunt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerJoin implements Listener {
	/** game instance */
	private Game game;
	
	/** default constructor */
	public ListenerJoin(Game game) {
		this.game = game;
	}
	
	/** join the default team */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.game.joinTeam(event.getPlayer());
	}
}
