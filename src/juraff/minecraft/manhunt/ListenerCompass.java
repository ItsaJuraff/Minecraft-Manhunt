package juraff.minecraft.manhunt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.block.Action;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class ListenerCompass implements Listener{

	@EventHandler
	public void onItemClick(PlayerInteractEvent event) {
		// get player
		Player user = event.getPlayer();

		if(event.getAction() == (Action.RIGHT_CLICK_AIR) || event.getAction() == (Action.RIGHT_CLICK_BLOCK)) {
			Player target = null;
			double lowest = 0.;
			
			// TODO should be changed to team
			// loop through players
			for(Player player2 : user.getWorld().getPlayers()) {
				if (player2 == user) {
					continue;
				}
				
				// get distance from player
				Location loc = player2.getLocation();
				double dist = loc.distance(user.getLocation());
				
				// find nearest player
				if(lowest > dist || target == null) {
					lowest = dist;
					target = player2;
				}
			}
			
			// send message to user
			user.sendMessage(ChatColor.GREEN + String.format("Tracking %s" , target.getName()));
			event.getPlayer().setCompassTarget(target.getLocation());
	    }
	}
}
