package systems.opalia.interfaces.database


final class Query private(clause: String, parameter: Map[String, Any])
                         (implicit executor: Executor) {

  def on[T](parameter: String, value: T)(implicit writer: FieldWriter[T]): Query =
    new Query(this.clause, this.parameter + (parameter -> writer(parameter, value).get))

  def execute(): Table =
    executor.execute(clause, parameter)
}

object Query {

  def apply(clause: String)(implicit executor: Executor): Query =
    new Query(clause, Map.empty)
}
