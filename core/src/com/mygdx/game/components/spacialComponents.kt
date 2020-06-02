package com.mygdx.game.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2

class PositionComponent(x: Float, y: Float) : Component {
  val vector = Vector2(x, y)
}

class MoveComponent(x: Float = 0f, y: Float = 0f) : Component {
  val vector: Vector2 = Vector2(x, y)
}

data class SizeComponent(val width: Float, val height: Float) : Component

private object SpacialMappers {
  val positionMapper = ComponentMapper.getFor(PositionComponent::class.java)
  val moveMapper = ComponentMapper.getFor(MoveComponent::class.java)
  val sizeMapper = ComponentMapper.getFor(SizeComponent::class.java)
}

val Entity.position get() = SpacialMappers.positionMapper.get(this).vector
val Entity.move get() = SpacialMappers.moveMapper.get(this).vector
val Entity.size get() = SpacialMappers.sizeMapper.get(this)

operator fun Vector2.component1() = this.x
operator fun Vector2.component2() = this.y