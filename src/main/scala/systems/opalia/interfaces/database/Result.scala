package systems.opalia.interfaces.database


sealed trait Result

trait SingleResult
  extends Result {

  def transform[T](f: Row => T): T
}

trait SingleOptResult
  extends Result {

  def transform[T](f: Row => T): Option[T]
}

trait IndexedSeqResult
  extends Result {

  def transform[T](f: Row => T): IndexedSeq[T]
}
