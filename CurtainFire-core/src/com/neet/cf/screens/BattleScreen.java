package com.neet.cf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.neet.cf.handlers.GameScreenManager;
import com.neet.cf.handlers.Transition;
import com.neet.cf.handlers.Transition.TransitionType;
import com.neet.cf.util.CFVars;

public class BattleScreen extends GameScreen
{
	public static String scriptPath = "";
	public BattleScreen(GameScreenManager gsm)
	{
		super(gsm);

	}
	@Override
	public void render()
	{ 	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.setProjectionMatrix(hudCam.combined);
		CFVars.font.setColor(Color.WHITE);
		CFVars.font.draw(sb, scriptPath, 10, 20);
		sb.end();
	}
	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInput()
	{
		if(Gdx.input.isKeyJustPressed(Keys.R))
		{
			gsm.setScreen(gsm.OVERWORLD, new Transition(TransitionType.SplitOut), false);

		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
		{
			gsm.prevScreen=gsm.BATTLE;
			gsm.transitionScreens(this, gsm.OPTIONS, new Transition(TransitionType.RectDown));
		}
	}

	@Override
	public void update(float dt)
	{
		handleInput();
	}

	@Override
	public void dispose()
	{
		
	}

}
