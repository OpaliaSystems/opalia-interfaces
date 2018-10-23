package systems.opalia.interfaces.database

import scala.util.Try


trait FieldWriter[T] {

  def apply(key: String, value: T): Try[Any]
}
