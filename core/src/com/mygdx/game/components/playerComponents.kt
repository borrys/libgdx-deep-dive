package com.mygdx.game.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity

class PlayerStateComponent(var currentState: PlayerState = PlayerState.IDLE) : Component

val positionMapper = ComponentMapper.getFor(PlayerStateComponent::class.java)
val Entity.state get() = positionMapper.get(this)

enum class PlayerState {
  IDLE, RUNNING
}