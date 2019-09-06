package systems.opalia.interfaces.worker

import scala.concurrent.Future


trait Producer {

  def topic: String

  def syncCall(message: Message): Message

  def asyncCall(message: Message): Future[Message]
}
