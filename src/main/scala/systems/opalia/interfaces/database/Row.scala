package systems.opalia.interfaces.database

import systems.opalia.interfaces.json.JsonAst


trait Row {

  def apply[T](column: String)(implicit reader: FieldReader[T]): T

  def toJson: JsonAst.JsonObject
}

