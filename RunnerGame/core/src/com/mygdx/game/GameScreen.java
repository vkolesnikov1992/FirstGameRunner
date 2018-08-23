package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen  {
    private RunnerGame runnerGame;
    private SpriteBatch batch;
    private Ground ground;
    private Background background;
    private Player player;
    private Stone stone;

    private BitmapFont font48;
    private BitmapFont font96;

    private boolean gameOver;
    private float time;

    private Music music;



    public GameScreen(RunnerGame runnerGame, SpriteBatch batch){
    this.runnerGame = runnerGame;
    this.batch = batch;


    }


    @Override
    public void show() {


        ground = new Ground();
        background = new Background();
        player = new Player();
        stone = new Stone();
        music = Gdx.audio.newMusic(Gdx.files.internal("Jumping bat.wav"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = -3;
        parameter.shadowColor = Color.BLACK;
        font48 = generator.generateFont(parameter);
        parameter.size = 96;
        font96 = generator.generateFont(parameter);
        generator.dispose();

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        ground.render(batch);
        player.render(batch);
        stone.render(batch, player.getPosition().x - player.getPlayerAnchor());
        font48.draw(batch, "SCORE: " + player.getScore() ,22, 702);
        if(gameOver){
            font96.draw(batch,"GAME OVER",360,382);
            font48.setColor(1, 1, 1, 0.5f + 0.5f * (float) Math.sin(time * 5.0f));
            font48.draw(batch, "Tap to RESTART", 450, 282);
            font48.setColor(1, 1, 1, 1);

        }
        batch.end();
    }

    public void update(float dt) {
        time += dt;
        if (!gameOver) {
            background.update();
            ground.update();
            player.update(dt);
            stone.update(dt, player.getPosition().x - player.getPlayerAnchor());
            for (int i = 0; i < stone.getStones().length; i++) {
               if (stone.getStones()[i].getRectangle().overlaps(player.getRectangle())) {

                    gameOver = true;
                    break;
                }
            }
        } else {
            if(Gdx.input.justTouched()){

                restart();
            }
        }

    }

    public void restart(){

        stone.restart();
        player.restart();
        gameOver = false;
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
       batch.dispose();
       runnerGame.dispose();
       music.dispose();





    }
}
