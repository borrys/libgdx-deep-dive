package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.components.AnimationComponent
import com.mygdx.game.components.TextureComponent
import com.mygdx.game.components.texture

class AnimationSystem
  : IteratingSystem(
    Family.all(
        AnimationComponent::class.java,
        TextureComponent::class.java
    ).get()) {

  override fun processEntity(entity: Entity, deltaTime: Float) {
    val animation = entity.getComponent(AnimationComponent::class.java)
    animation.updateTime(deltaTime)
    entity.texture.image = animation.currentFrame
  }
}