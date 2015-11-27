package com.neet.cf.handlers;

import com.neet.cf.CurtainFire;
import com.neet.cf.screens.GameScreen;
import com.neet.cf.screens.MainMenuScreen;
import com.neet.cf.screens.OverWorld;
import com.neet.cf.screens.TransitionScreen;

public class GameScreenManager 
{
	private CurtainFire game;
	
	private GameScreen[] gameScreens;
	
	public final int NUMSTATES=2;
	public final int START=0;
	public final int OVERWORLD = 1;

	
	private int currentScreen;
	
	private GameScreen currentGameScreen;
	
	public GameScreenManager(CurtainFire game)
	{
		this.game = game;
		gameScreens = new GameScreen[NUMSTATES];
		
		setScreen(START);
	}
	public CurtainFire getGame()
	{
		return game; 
	}
	public void update(float dt)
	{
		currentGameScreen.update(dt);
	}	
	public void render()
	{
		currentGameScreen.render();
	}
	private GameScreen getScreen(int screen)
	{
		if(screen==START) return new MainMenuScreen(this);
		if(screen==OVERWORLD) return new OverWorld(this);
		return null;
	}
	public void transitionScreens(GameScreen currentScreen, int nextScreen, Transition t)
	{
		CurtainFire.currentScreen= new TransitionScreen(this, currentScreen, nextScreen, t);
		currentGameScreen = CurtainFire.currentScreen;
	}
	//Sets screen to new screen so that it can be constructed and rendered to be the
	//background for transition.
	public void setScreen(int screen, Transition t)
	{
		setScreen(screen);
		transitionScreens(CurtainFire.currentScreen, screen, t);

	}
	public void setScreen(int screen)
	{
		if(gameScreens[screen]==null) //If the desired Screen does not exist
		{
			gameScreens[screen]=getScreen(screen); //Make it

		}
		CurtainFire.currentScreen = gameScreens[screen];
		currentGameScreen=gameScreens[screen];//Set current Screen to desired
		currentGameScreen.resume();//Resume Desired
		currentScreen=screen;//Update currentScreen array position
	}
	public void setScreen(int screen, boolean pause)
	{
		if(pause)
			currentGameScreen.pause(); //Pause calling Screen
		else if(!pause)
			disposeScreen(currentScreen);//Dispose calling Screen
		setScreen(screen);
	}
	

	public void disposeScreen(int screen)
	{
		GameScreen g= gameScreens[screen]; 
		gameScreens[screen]=null;
		((GameScreen) g).dispose();
	}
}