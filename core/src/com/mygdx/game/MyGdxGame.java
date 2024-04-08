package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.behavior.CoordXY;
import com.mygdx.game.controller.Main;
import com.mygdx.game.person.*;

import java.util.Random;

import static com.mygdx.game.controller.Main.all;

public class
MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon;
	Music music;
	Texture crossBowMan, mage, monk, peasant, rouge, sniper, spearMan;

	Main program;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fon = new Texture("fons/" + Fons.values()[new Random().nextInt(Fons.values().length)] +".png" );
		music = Gdx.audio.newMusic(Gdx.files.internal("music/paul-romero-rob-king-combat-theme-01.mp3"));
		music.setVolume(0.5f);
		music.play();



		crossBowMan = new Texture("pers/CrossBowMan.png");
		mage = new Texture("pers/Mage.png");
		monk = new Texture("pers/Monk.png");
		peasant = new Texture("pers/Peasant.png");
		rouge = new Texture("pers/Rouge.png");
		sniper = new Texture("pers/Sniper.png");
		spearMan = new Texture("pers/SpearMan.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(fon, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Отрисовываем персонажей
		for (PersonBase p : Main.all) {
			switch (p.getClass().getSimpleName()) {
				case "CrossBowMan":
					batch.draw(crossBowMan, p.getPosition().getX(), p.getPosition().getY());
					break;
				case "Mage":
					batch.draw(mage, p.getPosition().getX(), p.getPosition().getY());
					break;
				case "Monk":
					batch.draw(monk, p.getPosition().getX(), p.getPosition().getY());
					break;
				case "Peasant":
					batch.draw(peasant, p.getPosition().getX(), p.getPosition().getY());
					break;
				case "Rouge":
					batch.draw(rouge, p.getPosition().getX(), p.getPosition().getY());
					break;
				case "Sniper":
					batch.draw(sniper, p.getPosition().getX(), p.getPosition().getY());
					break;
				case "SpearMan":
					batch.draw(spearMan, p.getPosition().getX(), p.getPosition().getY());
					break;
				default:
					throw new IllegalArgumentException("Unknown person type: " + p.getClass().getSimpleName());
			}
		}
		batch.end();

}

	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
		crossBowMan.dispose();
		mage.dispose();
		monk.dispose();
		peasant.dispose();
		rouge.dispose();
		sniper.dispose();
		spearMan.dispose();
	}
}
