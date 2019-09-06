package systems.opalia.interfaces.vfs


case class Checksum(algorithm: String, value: IndexedSeq[Byte])
