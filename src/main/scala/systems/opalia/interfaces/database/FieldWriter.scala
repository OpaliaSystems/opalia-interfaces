package systems.opalia.interfaces.database

import scala.util.Try


trait FieldWriter[T] {

  def apply(parameter: String, value: T): Try[Any]
}
