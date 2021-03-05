package pms.core

/**
  *
  * @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 20 Jun 2018
  *
  */
object Email {

  def apply(em: String): Attempt[Email] =
    if (!em.contains("@"))
      Fail.invalid("Email must contain: @").raiseError[Attempt, Email]
    else
      new Email(em).pure[Attempt]

}

final class Email private (val plainTextEmail: String) {

  //generated by IntelliJ
  override def equals(other: Any): Boolean = other match {
    case that: Email =>
      plainTextEmail == that.plainTextEmail
    case _ => false
  }

  //generated by IntelliJ
  override def hashCode(): Int =
    31 * plainTextEmail.hashCode
}
