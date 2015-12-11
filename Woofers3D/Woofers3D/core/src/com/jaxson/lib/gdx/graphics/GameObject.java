package com.jaxson.lib.gdx.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public abstract class GameObject
{
	public abstract void dispose();

	protected void input()
	{

	}

	public void pause()
	{

	}

	public void render(SpriteBatch spriteBatch, ModelBatch modelBatch)
	{

	}

	public void resume()
	{

	}

	public void update(float dt)
	{
		input();
	}
}