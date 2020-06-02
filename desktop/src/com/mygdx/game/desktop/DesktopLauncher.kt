package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.MyGdxGame

object DesktopLauncher {
  @JvmStatic
  fun main(arg: Array<String>) {
    LwjglApplication(MyGdxGame(), LwjglApplicationConfiguration().apply {
      width = 1024
      height = 720
    })
  }
}