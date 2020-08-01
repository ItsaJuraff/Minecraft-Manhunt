package juraff.minecraft.manhunt;

import org.bukkit.command.CommandExecutor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandTest implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			// check if sender is player
			Player player = (Player) sender;
			
			// create new item stack
			ItemStack diamond = new ItemStack(Material.DIAMOND);
			diamond.setAmount(20);
			
			// give diamonds to player
			player.getInventory().addItem(diamond);
		}
		
		// if player uses command correctly return true
		return true;
	}
}
