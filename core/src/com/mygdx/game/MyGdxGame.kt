package com.mygdx.game

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.mygdx.game.components.*
import com.mygdx.game.systems.*

class MyGdxGame : ApplicationAdapter() {
  lateinit var batch: SpriteBatch
  lateinit var hudBatch: SpriteBatch
  lateinit var atlas: TextureAtlas
  lateinit var camera: OrthographicCamera
  lateinit var font: BitmapFont
  lateinit var engine: Engine

  lateinit var playerPositionComponent: PositionComponent

  override fun create() {
    batch = SpriteBatch()
    hudBatch = SpriteBatch()
    atlas = TextureAtlas("packed/assets.atlas")
    font = BitmapFont(Gdx.files.internal("font/font.fnt"))

    camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    val idleAnimation = Animation(0.05f, atlas.findRegions("idle"), Animation.PlayMode.LOOP)
    val runAnimation = Animation(0.05f, atlas.findRegions("run"), Animation.PlayMode.LOOP)

    engine = Engine()
    engine.addSystem(InputSystem())
    engine.addSystem(PlayerStateSystem(idleAnimation, runAnimation))
    engine.addSystem(MovementSystem())
    engine.addSystem(FlippingSystem())
    engine.addSystem(AnimationSystem())
    engine.addSystem(CameraSystem(camera, batch))
    engine.addSystem(RenderingSystem(batch))
    engine.addSystem(HudRenderingSystem(hudBatch, font))

    engine.addEntity(appleEntity())

    engine.addEntity(boxEntity(500f, 450f, 100f))
    engine.addEntity(boxEntity(0f, 250f, 70f))
    engine.addEntity(boxEntity(650f, -30f, 130f))

    engine.addEntity(Entity().apply {
      add(PositionComponent(100f, 100f))
      add(TextComponent("hello, world!"))
    })

    playerPositionComponent = PositionComponent(0f, 0f)
    engine.addEntity(Entity().apply {
      add(playerPositionComponent)
      add(SizeComponent(100f, 100f))
      add(AnimationComponent(idleAnimation))
      add(TextureComponent(idleAnimation.getKeyFrame(0f)))
      add(MoveComponent())
      add(ScaleComponent())
      add(PlayerStateComponent())
    })
  }

  private fun appleEntity(): Entity =
      Entity().apply {
        val animationComponent = AnimationComponent(Animation(
            0.05f,
            atlas.findRegions("apple").apply { removeRange(0, 4) },
            Animation.PlayMode.LOOP))
        add(animationComponent)
        add(TextureComponent(animationComponent.currentFrame))
        add(PositionComponent(300f, 350f))
        add(SizeComponent(75f, 75f))
      }

  private fun boxEntity(x: Float, y: Float, size: Float) =
      Entity().apply {
        add(TextureComponent(atlas.findRegion("box")))
        add(PositionComponent(x, y))
        add(SizeComponent(size, size))
      }

  override fun render() {
    Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    engine.update(Gdx.graphics.deltaTime)
  }

  override fun dispose() {
    batch.dispose()
    atlas.dispose()
  }
}