package systems.opalia.interfaces.database

import systems.opalia.interfaces.json.JsonAst


sealed trait Result

sealed trait EnrichedResult
  extends Result {

  def columns: IndexedSeq[String]

  def meta: JsonAst.JsonObject
}

trait IgnoredResult
  extends Result

trait SingleResult
  extends EnrichedResult {

  def transform[T](f: Row => T): T
}

trait SingleOptResult
  extends EnrichedResult {

  def transform[T](f: Row => T): Option[T]
}

trait IndexedSeqResult
  extends EnrichedResult {

  def transform[T](f: Row => T): IndexedSeq[T]
}

trait IndexedNonEmptySeqResult
  extends EnrichedResult {

  def transform[T](f: Row => T): IndexedSeq[T]
}
