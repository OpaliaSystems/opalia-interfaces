package systems.opalia.interfaces.database


trait QueryFactory {

  def newQuery(clause: String): Query
}
