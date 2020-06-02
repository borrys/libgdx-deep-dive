package com.mygdx.game

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion

data class AnimationComponent(val animation: Animation<TextureRegion>) : Component {
  private var animationTime = 0f

  fun updateTime(delta: Float) {
    animationTime += delta
  }

  val frame: TextureRegion
    get() = animation.getKeyFrame(animationTime)

}