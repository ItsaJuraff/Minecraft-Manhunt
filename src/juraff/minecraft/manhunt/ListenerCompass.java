package juraff.minecraft.manhunt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.block.Action;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class ListenerCompass implements Listener{

	Location target;
	String targetName;
	
	@EventHandler
	public void onItemClick(PlayerInteractEvent event) {
		
		Player user = event.getPlayer();
		//Location loc = new Location (user.getWorld(),0.0,0.0,0.0);
		

		if(event.getAction() == (Action.RIGHT_CLICK_AIR) || event.getAction() == (Action.RIGHT_CLICK_BLOCK)) {
	        
			Location loc;
			double lowest =60000000.;
			double dist;
			
			for(Player player2 : user.getWorld().getPlayers()) {
				
				if(player2 != user) {
					loc = player2.getLocation();
					dist = loc.distance(user.getLocation());
					
					if(lowest > dist) {
						lowest = dist;
						target = player2.getLocation();
						targetName = player2.getName();
					}
				}
			}
			
			user.sendMessage(ChatColor.GREEN + String.format("Tracking %s" , targetName));
			event.getPlayer().setCompassTarget(target);
	    }
	}
}
