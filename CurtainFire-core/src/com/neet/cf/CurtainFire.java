package com.neet.cf;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.neet.cf.handlers.GameInputProcessor;
import com.neet.cf.handlers.GameScreenManager;
import com.neet.cf.screens.GameScreen;
import com.neet.cf.util.CFVars;
import com.neet.cf.util.DialogBox;

public class CurtainFire extends ApplicationAdapter {
	public static AssetManager manager;
	public static GameScreen currentScreen;
	public static final float V_WIDTH = 240;
	public static final float V_HEIGHT =192;

	public static GameScreenManager gsm;
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	public CurtainFire(float width, float height)
	{
		CFVars.SCREEN_WIDTH=width;
		CFVars.SCREEN_HEIGHT=height;
		CFVars.V_WIDTH=V_WIDTH;
		CFVars.V_HEIGHT=V_HEIGHT;
	}
	@Override
	public void create()
	{
		manager = new AssetManager();
		loadAssets();
		manager.finishLoading();
		
		CFVars.font = manager.get("cfFont.fnt", BitmapFont.class);
		
		Gdx.input.setInputProcessor(new GameInputProcessor());
		
		DialogBox.box= manager.get("textBoxPurple.png", Texture.class);
		
		sb = new SpriteBatch();
		CFVars.DEFAULT_SB_COLOR=sb.getColor();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, CFVars.SCREEN_WIDTH,CFVars.SCREEN_HEIGHT);
		
		manager.get("pcboot.ogg", Sound.class).play(CFVars.VOLUME);
		gsm = new GameScreenManager(this);
	}
	public SpriteBatch getSpriteBatch()
	{
		return sb;
	}
	
	public OrthographicCamera getCamera()
	{
		return cam;
	}
	
	public OrthographicCamera getHUDCamera()
	{
		return hudCam;
	}
	private void loadAssets()
	{
		manager.load("player.png", Texture.class);
		manager.load("boyscout.png", Texture.class);
		manager.load("textBoxPurple.png", Texture.class);
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		manager.load("map001.tmx", TiledMap.class);
		manager.load("flowerIsland.tmx", TiledMap.class);
		manager.load("cfFont.fnt", BitmapFont.class);
		manager.load("pcboot.ogg", Sound.class);
		manager.load("blip.ogg", Sound.class);
		manager.load("pause.ogg", Sound.class);
		manager.load("unpause.ogg", Sound.class);
		manager.load("testMusic.ogg", Music.class);
	}
	public void render()
	{
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();

	}
	@Override
	public void dispose()
	{
		super.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}
	@Override
	public void pause()
	{
		super.pause();
	}
	@Override
	public void resume() 
	{
		super.resume();
	}

	
	
}