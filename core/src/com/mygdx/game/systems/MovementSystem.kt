package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.components.MoveComponent
import com.mygdx.game.components.PositionComponent
import com.mygdx.game.components.move
import com.mygdx.game.components.position

class MovementSystem : IteratingSystem(Family.all(MoveComponent::class.java, PositionComponent::class.java).get()) {

  override fun processEntity(entity: Entity, deltaTime: Float) {
    entity.position.add(entity.move)
  }
}