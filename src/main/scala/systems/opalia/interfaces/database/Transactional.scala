package systems.opalia.interfaces.database


trait Transactional {

  def withTransaction[T](block: (Executor) => T): T
}
