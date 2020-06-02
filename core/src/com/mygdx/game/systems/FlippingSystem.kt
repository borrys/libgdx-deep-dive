package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.components.MoveComponent
import com.mygdx.game.components.ScaleComponent
import com.mygdx.game.components.move
import com.mygdx.game.components.scale

class FlippingSystem : IteratingSystem(
    Family.all(
        MoveComponent::class.java,
        ScaleComponent::class.java
    ).get()
) {
  override fun processEntity(entity: Entity, deltaTime: Float) {
    entity.scale.vector.x = if (entity.move.x >= 0) 1f else -1f
  }
}