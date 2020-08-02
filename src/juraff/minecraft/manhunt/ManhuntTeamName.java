package juraff.minecraft.manhunt;

import org.bukkit.GameMode;

public enum ManhuntTeamName {
	Hunters(GameMode.SURVIVAL),
	Speedrunners(GameMode.SURVIVAL),
	Spectators(GameMode.SPECTATOR);
	
	private GameMode gamemode;
	
	ManhuntTeamName(GameMode gamemode) {
		this.gamemode = gamemode;
	}
	
	GameMode getGamemode() {
		return this.gamemode;
	}
}
