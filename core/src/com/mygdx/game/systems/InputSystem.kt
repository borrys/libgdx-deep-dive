package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.mygdx.game.components.MoveComponent
import com.mygdx.game.components.move

class InputSystem : IteratingSystem(
    Family.all(MoveComponent::class.java).get()
) {
  val SPEED = 200f

  override fun processEntity(entity: Entity, time: Float) {
    entity.move.x = 0f
    entity.move.y = 0f
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      entity.move.x = -SPEED * time
    } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      entity.move.x = SPEED * time
    }
    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      entity.move.y = SPEED * time
    } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      entity.move.y = -SPEED * time
    }
  }


}