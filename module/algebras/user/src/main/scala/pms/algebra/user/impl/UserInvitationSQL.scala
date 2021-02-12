package pms.algebra.user.impl

import doobie._
import doobie.implicits._

import pms.algebra.user._
import pms.core._

import pms.effects.implicits._

/**
  *
  * @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 24 Apr 2019
  *
  */
private[impl] object UserInvitationSQL {

  import UserAlgebraMetas._

  final case class UserInvitationRepr(
    email:           Email,
    role:            UserRole,
    invitationToken: UserInviteToken,
  )

  implicit val userReprComposite: Read[UserInvitationRepr] =
    Read[(Email, UserRole, UserInviteToken)]
      .map((t: (Email, UserRole, UserInviteToken)) => UserInvitationRepr.tupled.apply(t): UserInvitationRepr)

  def insert(repr: UserInvitationRepr): ConnectionIO[Unit] =
    sql"""INSERT INTO user_invitation(email, role, invitation_token) VALUES (${repr.email}, ${repr.role}, ${repr.invitationToken})""".update.run.void

  def findByToken(token: UserInviteToken): ConnectionIO[Option[UserInvitationRepr]] =
    sql"""SELECT email, role, invitation_token FROM user_invitation WHERE invitation_token=$token"""
      .query[UserInvitationRepr]
      .option

  def deleteByToken(tok: UserInviteToken): ConnectionIO[Unit] =
    sql"""DELETE FROM user_invitation WHERE invitation_token=$tok""".update.run.void
}
