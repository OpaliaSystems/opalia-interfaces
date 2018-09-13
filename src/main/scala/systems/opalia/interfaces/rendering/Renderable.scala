package systems.opalia.interfaces.rendering


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

  final def toBytes(implicit charset: String = Renderer.defaultCharset): Vector[Byte] =
    renderBytes(new ByteRenderer(charset)).result()

  def renderBytes(renderer: ByteRenderer): ByteRenderer
}
