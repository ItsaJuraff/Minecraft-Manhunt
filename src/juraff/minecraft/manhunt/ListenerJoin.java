package juraff.minecraft.manhunt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerJoin implements Listener {
	/** game instance */
	private ManhuntGame game;
	
	/** default constructor */
	public ListenerJoin(ManhuntGame game) {
		this.game = game;
	}
	
	/** join the default team */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.game.joinTeam(event.getPlayer());
	}
}
