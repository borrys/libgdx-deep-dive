package com.mygdx.game;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
  float x;
  float y;

  public PositionComponent(float x, float y) {
    this.x = x;
    this.y = y;
  }
}
