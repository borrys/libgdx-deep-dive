package com.mygdx.game.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2

class TextureComponent(var image: TextureRegion) : Component

class AnimationComponent(animation: Animation<out TextureRegion>) : Component {
  private var animationTime = 0f

  var animation = animation
    set(newVal) {
      if (newVal != field) {
        field = newVal
        animationTime = 0f
      }
    }

  fun updateTime(delta: Float) {
    animationTime += delta
  }

  val currentFrame get() = animation.getKeyFrame(animationTime)
}

class TextComponent(var value: String) : Component

class ScaleComponent(scaleX: Float = 1f, scaleY: Float = 1f) : Component {
  var vector = Vector2(scaleX, scaleY)
}

val NO_SCALE = ScaleComponent()

private object TextureMappers {
  val textureMapper = ComponentMapper.getFor(TextureComponent::class.java)
  val animationMapper = ComponentMapper.getFor(AnimationComponent::class.java)
  val textMapper = ComponentMapper.getFor(TextComponent::class.java)
  val scaleMapper = ComponentMapper.getFor(ScaleComponent::class.java)
}

val Entity.texture: TextureComponent
  get() = TextureMappers.textureMapper.get(this)
val Entity.animation: AnimationComponent
  get() = TextureMappers.animationMapper.get(this)
val Entity.scale: ScaleComponent
  get() = TextureMappers.scaleMapper.get(this) ?: NO_SCALE
val Entity.text: TextComponent
  get() = TextureMappers.textMapper.get(this)