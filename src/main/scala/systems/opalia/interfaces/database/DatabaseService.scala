package systems.opalia.interfaces.database


trait DatabaseService {

  def newTransactional(): Transactional

  def backup(): Unit
}
