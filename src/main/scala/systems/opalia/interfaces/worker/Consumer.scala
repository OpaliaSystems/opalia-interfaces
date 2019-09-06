package systems.opalia.interfaces.worker


trait Consumer {

  def topic: String

  def apply(message: Message): Message

  def register(): Unit

  def unregister(): Unit
}
