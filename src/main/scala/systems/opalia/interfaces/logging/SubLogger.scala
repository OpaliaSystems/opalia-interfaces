package systems.opalia.interfaces.logging


trait SubLogger {

  val name: String

  def apply(message: => String): Unit

  def apply(message: => String, throwable: Throwable): Unit
}
