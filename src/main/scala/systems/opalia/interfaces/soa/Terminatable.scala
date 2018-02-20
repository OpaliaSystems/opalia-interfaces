package systems.opalia.interfaces.soa

import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.{Future, Promise}
import scala.util.Try


trait Terminatable {

  private val promise = Promise[Unit]()
  private val down: AtomicBoolean = new AtomicBoolean(false)

  def completelyUp: Boolean =
    true

  def completelyDown: Boolean =
    promise.isCompleted

  def awaitDown(): Future[Unit] =
    promise.future

  def shutdown(): Boolean = {

    if (!completelyUp)
      false
    else if (down.getAndSet(true))
      false
    else {

      promise.complete(Try(shutdownTask()))
      true
    }
  }

  protected def shutdownTask()
}
