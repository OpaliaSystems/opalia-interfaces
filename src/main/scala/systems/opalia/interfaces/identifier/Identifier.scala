package systems.opalia.interfaces.identifier

import java.util.Objects
import systems.opalia.interfaces.rendering._


trait Identifier
  extends IndexedSeq[Byte]
    with StringRenderable
    with ByteRenderable {

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

  override def renderBytes(renderer: ByteRenderer): ByteRenderer = {

    renderer ++= data
  }
}
