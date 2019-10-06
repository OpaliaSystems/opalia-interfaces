package systems.opalia.interfaces.misc


sealed abstract class Gender(name: String) {

  override def toString: String =
    name
}

object Gender {

  case object Female
    extends Gender("female")

  case object Male
    extends Gender("male")

  case class Other(name: String)
    extends Gender(name)

  def withNameOpt(string: String): Option[Gender] =
    if (string.equalsIgnoreCase("female"))
      Some(Female)
    else if (string.equalsIgnoreCase("male"))
      Some(Male)
    else if (string.matches("[a-z]+"))
      Some(Other(string.toLowerCase))
    else
      None

  def withName(string: String): Gender =
    withNameOpt(string).getOrElse(throw new IllegalArgumentException(s"Cannot parse gender with name “$string”."))
}
