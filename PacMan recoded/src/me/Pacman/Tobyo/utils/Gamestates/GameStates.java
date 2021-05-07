package me.Pacman.Tobyo.utils.Gamestates;

public enum GameStates {

	Lobby,
	Ingame,
	Ending;
	
	public static GameStates gamestate;
	
	public static void setGamestate(GameStates gamestate) {
		GameStates.gamestate = gamestate;
	}
	
	public static GameStates getGamestate() {
		return gamestate;
	}
	
}
