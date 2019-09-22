package systems.opalia.interfaces.scripting

import systems.opalia.interfaces.soa.Terminatable


trait ScriptSession
  extends Terminatable[Unit] {

  def context: ScriptContext

  def enter(): Unit

  def leave(): Unit

  def withContext[T](block: (ScriptContext) => T): T = {

    enter()

    try {

      block(context)

    } finally {

      leave()
    }
  }
}
