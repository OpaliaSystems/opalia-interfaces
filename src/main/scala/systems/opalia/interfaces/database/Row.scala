package systems.opalia.interfaces.database

import scala.util.{Failure, Success}
import systems.opalia.interfaces.json.JsonAst


trait Row {

  def apply[T](column: String)(implicit reader: FieldReader[T]): T = {

    val entry =
      find(column)
        .map(x => Success(x))
        .getOrElse(Failure(new IllegalArgumentException("Cannot find column " + column + ".")))

    (for {
      value <- entry
      result <- reader(column, value)
    } yield result).get
  }

  protected def find(column: String): Option[Any]

  def toJson: JsonAst.JsonObject
}
