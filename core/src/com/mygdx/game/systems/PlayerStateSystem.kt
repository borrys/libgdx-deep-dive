package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.components.*

class PlayerStateSystem : IteratingSystem(
    Family.all(PlayerStateComponent::class.java).get()
) {

  override fun processEntity(player: Entity, deltaTime: Float) {
    val event = if (player.move.isZero()) PlayerStateEvent.STOP else PlayerStateEvent.RUN
    player.state.handle(event)
    player.animation.animation = player.state.animation
  }

  private val Entity.newState
    get() = if (this.move.isZero) PlayerState.IDLE else PlayerState.RUNNING
}

