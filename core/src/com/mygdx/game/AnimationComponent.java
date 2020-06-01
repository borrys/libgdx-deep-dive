package com.mygdx.game;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationComponent implements Component {
  final Animation<TextureRegion> animation;
  float animationTime;

  public AnimationComponent(Animation<TextureRegion> animation) {
    this.animation = animation;
  }

  void updateTime(float delta) {
    animationTime += delta;
  }

  TextureRegion getFrame() {
    return animation.getKeyFrame(animationTime);
  }
}
