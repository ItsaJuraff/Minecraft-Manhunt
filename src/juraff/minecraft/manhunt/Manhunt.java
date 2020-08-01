package juraff.minecraft.manhunt;
import org.bukkit.plugin.java.JavaPlugin;

public class Manhunt extends JavaPlugin {
	@Override
	public void onEnable() {
		// create object to holds teams
		Team team = new Team();
		
		// register commands
		this.getCommand("test").setExecutor(new CommandTest());
		this.getCommand("team").setExecutor(new CommandTeam(team));
	}
	
	@Override
	public void onDisable() {
	}

}
