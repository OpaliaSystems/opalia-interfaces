package systems.opalia.interfaces.database

import scala.reflect.ClassTag


final class Query private(clause: String, parameters: Map[String, Any])
                         (implicit executor: Executor) {

  def on[T](key: String, value: T)(implicit writer: FieldWriter[T]): Query =
    new Query(clause, parameters + (key -> writer(key, value).get))

  def execute[R <: Result : ClassTag](): R =
    executor.execute[R](clause, parameters)
}

object Query {

  def apply(clause: String)(implicit executor: Executor): Query =
    new Query(clause, Map.empty)
}
