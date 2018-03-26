package systems.opalia.interfaces.database


trait Table {

  def columns: IndexedSeq[String]

  def meta: SingleResult

  def fetchSingle: SingleResult

  def fetchSingleOpt: SingleOptResult

  def fetchSeq: IndexedSeqResult

  def fetchNonEmptySeq: IndexedSeqResult
}
