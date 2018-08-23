package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    class BGPicture{
        private Texture texture;
        private Vector2 position;

        public BGPicture(Vector2 position){
            texture = new Texture("bg.png");
            this.position = position;
        }
    }

    private int speed;
    private BGPicture[] bgPictures;

   public Background(){
       speed = 1;
       bgPictures = new BGPicture[2];
       bgPictures[0] = new BGPicture(new Vector2(0,0));
       bgPictures[1] = new BGPicture(new Vector2(1280,0));

   }

   public void render(SpriteBatch batch){
       for (int i = 0; i < bgPictures.length ; i++) {
           batch.draw(bgPictures[i].texture, bgPictures[i].position.x, bgPictures[i].position.y);
       }
   }
   public void update(){
       for (int i = 0; i < bgPictures.length ; i++) {
           bgPictures[i].position.x -= speed;
       }

       if(bgPictures[0].position.x == -1280){
           bgPictures[0].position.x = 0;
           bgPictures[1].position.x = 1280;
       }
   }

}

