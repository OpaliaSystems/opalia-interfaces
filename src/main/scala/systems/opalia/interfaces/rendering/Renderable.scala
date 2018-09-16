package systems.opalia.interfaces.rendering

import java.nio.ByteOrder


sealed trait Renderable
  extends Serializable

trait StringRenderable
  extends Renderable {

  override final def toString: String =
    renderString(new StringRenderer()).result()

  def renderString(renderer: StringRenderer): StringRenderer
}

trait ByteRenderable
  extends Renderable {

  final def toBytes(implicit charset: String = Renderer.defaultCharset,
                    byteOrder: ByteOrder = Renderer.defaultByteOrder): Vector[Byte] =
    renderBytes(new ByteRenderer(charset, byteOrder)).result()

  def renderBytes(renderer: ByteRenderer): ByteRenderer
}
