package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture texture;
    private Texture textureJump;
    private Vector2 position;
    private Vector2 speed;
    private float time;
    private Rectangle rectangle;

    private Sound jumpSound;


    Ground ground;
    private int score;



    private final int WIDTH = 150;
    private final int HEIGHT = 150;
    private int playerAnchor = 200;


    public Rectangle getRectangle() {
        return rectangle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getPlayerAnchor() {
        return playerAnchor;
    }

    public int getScore() {
        return score;
    }



    public Player(){
        texture = new Texture("Player.png");
        textureJump = new Texture("playerJump.png");
        position = new Vector2(0,120);
        speed = new Vector2(50,0);
        score = 0;
        ground = new Ground();
        rectangle = new Rectangle(position.x + WIDTH / 4, position.y, WIDTH / 2, HEIGHT);
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("laser.wav"));



    }
    public void restart(){
        position.set(0, 120);
        score = 0;
        speed.set(50,0);
        rectangle.setPosition(position);
    }

    public void render(SpriteBatch batch){
            int frame = (int)(time/0.1f); //если с запуска программы прошло 0.2 секунды, делим 0.2 / 0.1 = 2, т.е. берем второй кадр
            frame = frame % 8; //количество кадров

            if(position.y > ground.groundHeight()) {
                batch.draw(textureJump, playerAnchor, position.y, 4 * 150, 0, WIDTH, HEIGHT); //анимация с прыжком фуфло. оставил один кадр на прыжок
            }
            else {
                batch.draw(texture, playerAnchor, position.y, frame * 150, 0, WIDTH, HEIGHT);
            }

    }
    public void update(float dt){

        if(position.y > ground.groundHeight()){
            speed.y -=720 * dt; //"гравитация"
        }
        else {

            speed.y = 0;

            if(Gdx.input.justTouched()){
                speed.y +=550; //прыжок
                jumpSound.play();
                score++;
            }
        }

        time +=speed.x / 50 * dt; //скорость анимации
        position.mulAdd(speed,dt);//mulAdd к кординатам добавить speed * dt




        rectangle.setPosition(position.x + WIDTH / 4, position.y);

    }
    public void dispose(){
        jumpSound.dispose();
        textureJump.dispose();
        texture.dispose();
    }
}
