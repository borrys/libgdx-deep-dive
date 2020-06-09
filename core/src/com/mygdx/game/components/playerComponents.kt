package com.mygdx.game.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion

class PlayerStateComponent(
    val animationMap: Map<PlayerState, Animation<out TextureRegion>>,
    var currentState: PlayerState = PlayerState.IDLE
) : Component {
  private val transitionMap =
      mapOf(
          PlayerState.IDLE to mapOf(PlayerStateEvent.RUN to PlayerState.RUNNING),
          PlayerState.RUNNING to mapOf(PlayerStateEvent.STOP to PlayerState.IDLE)
      )

  fun handle(event: PlayerStateEvent) {
    currentState = transitionMap[currentState]?.get(event) ?: currentState
  }

  val animation
    get() = animationMap[currentState]!!
}

val positionMapper = ComponentMapper.getFor(PlayerStateComponent::class.java)
val Entity.state get() = positionMapper.get(this)

enum class PlayerState() {
  IDLE, RUNNING
}

enum class PlayerStateEvent {
  RUN, STOP
}