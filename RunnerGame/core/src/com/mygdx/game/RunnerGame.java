package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RunnerGame extends Game  {
	private SpriteBatch batch;
	private GameScreen gameScreen;
	private Viewport viewport; //с помошью vieport можно адаптировать экран при смене разрешения.

	public Viewport getViewport() {
		return viewport;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this,batch);
		viewport = new FitViewport(1280,720); // FitVieport - адаптирует содержимое на экране под нужный размер
		setScreen(gameScreen); //срабатывает метод Show из GameScreen

	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime(); // время которое прошло между сменой двух кадров(FPS)

		getScreen().render(dt);
	}

	@Override
	public void dispose () {  //сборшик мусора не работает с видео памятью, очишаем видеопамять
		batch.dispose();
		getScreen().dispose();

	}
}
