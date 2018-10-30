package systems.opalia.interfaces.logging


object LogLevel
  extends Enumeration {

  val OFF = Value("OFF")
  val ERROR = Value("ERROR")
  val WARNING = Value("WARNING")
  val INFO = Value("INFO")
  val DEBUG = Value("DEBUG")

  def withNameOpt(string: String): Option[Value] =
    values.find(_.toString == string)
}
