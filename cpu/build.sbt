enablePlugins(Example)

import scala.meta._
exampleSuperTypes := exampleSuperTypes.value.map {
  case ctor"_root_.org.scalatest.FreeSpec" =>
    ctor"_root_.org.scalatest.AsyncFreeSpec"
  case otherTrait =>
    otherTrait
}

exampleSuperTypes += ctor"_root_.org.scalatest.Inside"

libraryDependencies += ("org.lwjgl" % "lwjgl" % "3.2.3" % Optional).jar().classifier {
  import scala.util.Properties._
  if (isMac) {
    "natives-macos"
  } else if (isLinux) {
    "natives-linux"
  } else if (isWin) {
    "natives-windows"
  } else {
    throw new MessageOnlyException(s"lwjgl does not support $osName")
  }
}

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

fork := true
