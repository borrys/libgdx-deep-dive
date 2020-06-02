package com.mygdx.game

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.math.Vector2

class MyGdxGame : ApplicationAdapter() {
  lateinit var batch: SpriteBatch
  lateinit var hudBatch: SpriteBatch
  lateinit var img: TextureRegion
  lateinit var box: TextureRegion
  lateinit var atlas: TextureAtlas
  lateinit var appleAnimation: Animation<TextureRegion>
  lateinit var runningAnimation: Animation<TextureRegion>
  lateinit var camera: OrthographicCamera
  lateinit var font: BitmapFont
  lateinit var engine: Engine

  var playerPosition = Vector2()
  var speedX = 200f
  var speedY = 200f
  var running = false
  var runningTime = 0f

  override fun create() {
    batch = SpriteBatch()
    hudBatch = SpriteBatch()
    atlas = TextureAtlas("packed/assets.atlas")
    box = atlas.findRegion("box")
    img = atlas.findRegion("idle")
    val appleFrames = atlas.findRegions("apple")
    appleFrames.removeRange(0, 4)
    appleAnimation = Animation(0.05f, appleFrames, Animation.PlayMode.LOOP)
    runningAnimation = Animation(0.05f, atlas.findRegions("run"), Animation.PlayMode.LOOP)
    font = BitmapFont(Gdx.files.internal("font/font.fnt"))

    camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    engine = Engine()
    engine.addSystem(AnimationSystem())
    engine.addSystem(AnimationRenderingSystem(batch))

    engine.addEntity(Entity().apply {
      add(AnimationComponent(appleAnimation))
      add(PositionComponent(300f, 350f))
    })
  }

  override fun render() {
    val time = Gdx.graphics.deltaTime
    val move = Vector2()
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      move.x = -speedX * time
    } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      move.x = speedX * time
    }
    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      move.y = speedY * time
    } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      move.y = -speedY * time
    }
    val cameraScale = if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) 2f else 1f
    camera.viewportWidth = Gdx.graphics.width * cameraScale
    camera.viewportHeight = Gdx.graphics.height * cameraScale
    running = !move.isZero
    playerPosition.add(move)
    if (running) {
      runningTime += time
    } else {
      runningTime = 0f
    }
    camera.position.x = playerPosition.x
    camera.position.y = playerPosition.y + 200
    Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    camera.update()
    batch.projectionMatrix = camera.combined
    engine.update(time)
    batch.begin()
    if (running) {
      val frame = runningAnimation.getKeyFrame(runningTime)
      batch.draw(frame, playerPosition.x, playerPosition.y,
          PLAYER_SIZE / 2, PLAYER_SIZE / 2,
          PLAYER_SIZE, PLAYER_SIZE,
          if (move.x >= 0) 1f else -1f, 1f, 0f)
    } else {
      batch.draw(img, playerPosition.x, playerPosition.y, 100f, 100f)
    }
    batch.draw(box, 500f, 450f, 100f, 100f)
    batch.draw(box, 0f, 250f, 70f, 70f)
    batch.draw(box, 650f, -30f, 130f, 130f)
    batch.end()
    hudBatch.begin()
    font.draw(hudBatch, "Hello, world!", 100f, 100f)
    hudBatch.end()
  }

  override fun dispose() {
    batch.dispose()
    atlas.dispose()
  }

  companion object {
    private const val PLAYER_SIZE = 100f
  }
}