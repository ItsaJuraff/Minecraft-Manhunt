package juraff.minecraft.manhunt;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			// check if sender is player
			Player player = (Player) sender;

			player.sendMessage(String.format("Saturation: %f", player.getSaturation()));
		
		}
		// if player uses command correctly return true
		return true;
	}
}
