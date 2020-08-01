package juraff.minecraft.manhunt;
import org.bukkit.plugin.java.JavaPlugin;

public class Manhunt extends JavaPlugin {
	@Override
	public void onEnable() {
		// register commands
		this.getCommand("test").setExecutor(new CommandTest());
	}
	
	@Override
	public void onDisable() {
	}

}
