package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Stone {
    private final int WIDTH = 80;
    private final int HEIGHT = 100;
    private Stones[] stones;
    private Ground ground;
    private int speed;
    private Rectangle rectangle;


    public class Stones{
        private Texture texture;
        private Vector2 position;

        public Stones(Vector2 position){
            this.position = position;
            this.texture = new Texture("stone.png");

        }
        public Rectangle getRectangle() {
            rectangle = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
            return rectangle;
        }

    }


    public Stones[] getStones() {
        return stones;
    }

    public Stone() {
        ground = new Ground();
        stones = new Stones[10];
        stones[0] = new Stones(new Vector2(900,ground.groundHeight()));
        speed = 5;


        for (int i = 1; i < 10 ; i++) {
            stones[i] = new Stones(new Vector2(stones[i-1].position.x + MathUtils.random(500,900), ground.groundHeight()));
        }


    }
    public void restart(){
        stones[0] = new Stones(new Vector2(900,ground.groundHeight()));
        for (int i = 1; i < 10 ; i++) {
            stones[i] = new Stones(new Vector2(stones[i-1].position.x + MathUtils.random(500,900), ground.groundHeight()));
        }
    }

    public float getRightestStone(){
        float maxValue = 0.0f;
        for (int i = 0; i < stones.length; i++) {
            if (maxValue < stones[i].position.x){
                maxValue = stones[i].position.x;
            }
        }
        return maxValue;
    }

    public void render(SpriteBatch batch, float worldX){


        for (int i = 0; i < stones.length ; i++) {
            batch.draw(stones[i].texture, stones[i].position.x - worldX, stones[i].position.y);  // РАЗОБРАТЬСЯ
        }
    }

    public void update(float dt, float worldX){
        for (int i = 0; i < stones.length ; i++) {

            stones[i].position.x -= speed; //костыль
            //stones[i].getRectangle().setPosition(stones[i].position.x, stones[i].position.y);
        }
        for (int i = 0; i < stones.length ; i++) {
            if (stones[i].position.x <= worldX - 80){
                stones[i].position.x = getRightestStone() + MathUtils.random(500,900);

            }
        }

    }
}
