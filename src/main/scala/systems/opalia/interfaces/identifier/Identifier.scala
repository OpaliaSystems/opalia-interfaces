package systems.opalia.interfaces.identifier

import java.util.Objects


trait Identifier
  extends IndexedSeq[Byte] {

  protected val data: Vector[Byte]

  override def apply(index: Int): Byte =
    data(index)

  override def length: Int =
    data.length

  override def equals(that: Any): Boolean =
    that match {

      case that: Identifier if (this.getClass == that.getClass && this.sameElements(that)) => true
      case _ => false
    }

  override def hashCode: Int =
    Objects.hash(data.map(Byte.box): _*)
}
