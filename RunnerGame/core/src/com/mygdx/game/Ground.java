package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Ground {
    class GPictures{
        private Texture texture;
        private Vector2 position;


        public GPictures(Vector2 position){
            this.position = position;
            texture = new Texture("ground.png");
        }
    }
    private int groundHeight = 120;



    private GPictures[] gPictures;
    private float speed;



    public Ground(){
        gPictures = new GPictures[5];
        speed = 6;
        //нужно обьявить кординаты и элементы массива через цикл
        gPictures[0] = new GPictures(new Vector2(0,0));
        gPictures[1] = new GPictures(new Vector2(384,0));
        gPictures[2] = new GPictures(new Vector2(768,0));
        gPictures[3] = new GPictures(new Vector2(1152,0));
        gPictures[4] = new GPictures(new Vector2(1536,0));




    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < gPictures.length ; i++) {
            batch.draw(gPictures[i].texture, gPictures[i].position.x, gPictures[i].position.y);
        }
    }
    public void update(){
        for (int i = 0; i < gPictures.length ; i++) {
            gPictures[i].position.x -=speed;
        }
        if(gPictures[0].position.x <=  -384)
            gPictures[0].position.x = 1536;

           if(gPictures[1].position.x <=  -384)
               gPictures[1].position.x = 1536;

            if(gPictures[2].position.x <= -384)
                gPictures[2].position.x = 1536;

            if(gPictures[3].position.x <=  -384)
                gPictures[3].position.x = 1536;

            if(gPictures[4].position.x <=  -384)
                gPictures[4].position.x = 1536;



    }

    public int groundHeight() {
        return groundHeight;
    }
}
