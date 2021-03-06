package juraff.minecraft.manhunt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.event.block.Action;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

public class ListenerCompass implements Listener{
	/**
	 * default constructor
	 * @param game manhunt game
	 */
	public ListenerCompass() {}

	@EventHandler
	public void onItemClick(PlayerInteractEvent event) {
		// get player
		Player user = event.getPlayer();

		if (event.getAction() == (Action.RIGHT_CLICK_AIR) || event.getAction() == (Action.RIGHT_CLICK_BLOCK)) {
			// check if item in hand is compass
			if (event.getMaterial() != Material.COMPASS) {
				return;
			}
			// check if player is part of hunters
			ManhuntTeam hunters = ManhuntTeam.Hunters;
			if (!hunters.checkPlayer(user)) {
				return;
			}
			
			// check if compass was given by game
			if (((CompassMeta) event.getItem().getItemMeta()).isLodestoneTracked()) {
				return;
			}
			
			// init variables
			Player target = null;
			double lowest = 0.;

			
			// get runner team
			ManhuntTeam runners = ManhuntTeam.Speedrunners;
			
			// loop through players
			for(Player player2 : runners.getAllPlayers()) {
				// checks
				if (player2 == user) {
					continue;
				}
				if (player2.getWorld() != user.getWorld()) {
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
			
			if (target == null) {
				// send message when player not found
				user.sendMessage(ChatColor.RED + "No player found");
			} else {
				// send message to user
				CompassMeta locMeta = (CompassMeta) event.getItem().getItemMeta();
				locMeta.setLodestone(target.getLocation());
				locMeta.setLodestoneTracked(false);
				event.getItem().setItemMeta(locMeta);
				user.sendMessage(ChatColor.GREEN + String.format("Tracking %s" , target.getName()));
			}
	    }
	}
}
