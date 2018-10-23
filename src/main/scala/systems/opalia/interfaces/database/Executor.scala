package systems.opalia.interfaces.database

import scala.reflect.ClassTag


trait Executor {

  def execute[R <: Result : ClassTag](clause: String, parameters: Map[String, Any]): R
}
