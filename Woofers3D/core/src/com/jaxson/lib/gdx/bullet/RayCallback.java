package com.jaxson.lib.gdx.bullet;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;

public class RayCallback extends ClosestRayResultCallback
{
	private static final float MAX_DISTANCE = 50f;

	private Vector3 rayStart, rayEnd;

	public RayCallback()
	{
		super(Vector3.Zero, Vector3.Z);
		this.rayStart = new Vector3();
		this.rayEnd = new Vector3();
	}

	public btCollisionObject getCollisionObject(float x, float y, Camera camera, PhysicsWorld world)
	{
		return getCollisionObject(camera.getPickRay(x, y), world);
	}

	public btCollisionObject getCollisionObject(Ray ray, PhysicsWorld world)
	{
		rayStart.set(ray.origin);
		rayEnd.set(ray.direction);
		rayEnd.scl(MAX_DISTANCE);
		rayEnd.add(rayStart);

		reset();
		setRayFromWorld(rayStart);
		setRayToWorld(rayEnd);
		world.rayTest(rayStart, rayEnd, this);

		if (hasHit()) return getCollisionObject();
		return null;
	}

	public void reset()
	{
		setCollisionObject(null);
		setClosestHitFraction(1f);
	}

}
