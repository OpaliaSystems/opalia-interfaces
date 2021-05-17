package systems.opalia.interfaces.database


trait Transactional {

  def withTransaction[T](block: (QueryFactory) => T): T
}
