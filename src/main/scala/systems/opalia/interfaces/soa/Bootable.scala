package systems.opalia.interfaces.soa

import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.{Future, Promise}
import scala.util.Try


trait Bootable
  extends Terminatable {

  private val promise = Promise[Unit]()
  private val up: AtomicBoolean = new AtomicBoolean(false)

  override def completelyUp: Boolean =
    promise.isCompleted

  def awaitUp(): Future[Unit] =
    promise.future

  def setup(): Boolean = {

    if (up.getAndSet(true))
      false
    else {

      promise.complete(Try(setupTask()))
      true
    }
  }

  protected def setupTask()
}
