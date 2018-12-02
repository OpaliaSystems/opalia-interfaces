package systems.opalia.interfaces.soa

import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.{Future, Promise}
import scala.util.Try


trait Terminatable[T] {

  private val promise = Promise[T]()
  private val down: AtomicBoolean = new AtomicBoolean(false)

  def completelyUp: Boolean =
    true

  final def completelyDown: Boolean =
    promise.isCompleted

  final def awaitDown(): Future[T] =
    promise.future

  final def shutdown(): Boolean = {

    if (!completelyUp)
      false
    else if (down.getAndSet(true))
      false
    else {

      promise.complete(Try(shutdownTask()))
      true
    }
  }

  protected def shutdownTask(): T
}
