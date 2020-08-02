package juraff.minecraft.manhunt;

import org.bukkit.GameMode;

public enum ManhuntTeamName {
	Hunters(0, GameMode.SURVIVAL),
	Speedrunners(1, GameMode.SURVIVAL),
	Spectators(2, GameMode.SPECTATOR);
	
	private int index;
	private GameMode gamemode;
	
	ManhuntTeamName(int index, GameMode gamemode) {
		this.gamemode = gamemode;
		this.index = index;
	}
	
	GameMode getGamemode() {
		return this.gamemode;
	}
	
	int getIndex() {
		return this.index;
	}
}
