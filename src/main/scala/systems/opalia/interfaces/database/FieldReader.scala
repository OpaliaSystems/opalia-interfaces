package systems.opalia.interfaces.database

import scala.util.Try


trait FieldReader[T] {

  def apply(column: String, value: Any): Try[T]
}
