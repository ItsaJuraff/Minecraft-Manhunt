package juraff.minecraft.manhunt;
import org.bukkit.plugin.java.JavaPlugin;

public class Manhunt extends JavaPlugin {
	@Override
	public void onEnable() {
		// create object to holds teams
		ManhuntGame game = new ManhuntGame();
		
		// register events
		this.getServer().getPluginManager().registerEvents(new ListenerJoin(game), this);
		this.getServer().getPluginManager().registerEvents(new ListenerCompass(), this);
		
		// register commands
		this.getCommand("test").setExecutor(new CommandTest());
		this.getCommand("manhunt").setExecutor(new CommandManhunt(game));
	}
	
	@Override
	public void onDisable() { }

}
