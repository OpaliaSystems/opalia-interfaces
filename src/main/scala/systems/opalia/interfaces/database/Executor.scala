package systems.opalia.interfaces.database


trait Executor {

  def execute(clause: String, parameter: Map[String, Any]): Table
}
