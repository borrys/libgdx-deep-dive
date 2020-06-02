package com.mygdx.game

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem

class AnimationSystem
  : IteratingSystem(
    Family.all(
        AnimationComponent::class.java
    ).get()) {
  override fun processEntity(entity: Entity, deltaTime: Float) {
    val animation = entity.getComponent(AnimationComponent::class.java)
    animation.updateTime(deltaTime)
  }
}