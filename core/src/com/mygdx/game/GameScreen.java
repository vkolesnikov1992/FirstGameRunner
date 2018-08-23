package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    private RunnerGame runnerGame;
    private SpriteBatch batch;
    private Texture textureBackground;
    private Texture textureGround;
    private float speedGround;
    private float speedBackground;



    public GameScreen(RunnerGame runnerGame, SpriteBatch batch){
    this.runnerGame = runnerGame;
    this.batch = batch;


    }


    @Override
    public void show() {

        textureGround = new Texture("ground.png");
        textureBackground = new Texture("bg.png");

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(textureBackground,0,0);
        for (int i = 0; i < 5 ; i++) {
            batch.draw(textureGround,i * 384 - speedGround % 200,0);
        }

        batch.end();
    }

    public void update(float dt){
        speedGround +=200.0f * dt;
    }

    @Override
    public void resize(int width, int height) {
    runnerGame.getViewport().update(width, height,true);
    runnerGame.getViewport().apply();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        textureGround.dispose();


    }
}
