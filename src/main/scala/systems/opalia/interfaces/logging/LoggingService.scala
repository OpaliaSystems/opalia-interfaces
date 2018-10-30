package systems.opalia.interfaces.logging


trait LoggingService {

  def newLogger(name: String): Logger
}
