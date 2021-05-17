package systems.opalia.interfaces.database

import scala.reflect.ClassTag


trait Query {

  def on[T](key: String, value: T)(implicit writer: FieldWriter[T]): Query

  def execute[R <: Result : ClassTag](): R
}
