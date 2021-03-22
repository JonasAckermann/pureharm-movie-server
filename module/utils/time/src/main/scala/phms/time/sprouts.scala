package phms.time

import phms._

trait SproutTimestamp extends Sprout[OffsetDateTime] {
  def now[F[_]](implicit F:      Applicative[F], t: Time[F]): F[Type] = t.now.map(this.newType)
  def tomorrow[F[_]](implicit F: Applicative[F], t: Time[F]): F[Type] = t.now.map(n => this.newType(n.plusDays(1)))
}

trait SproutSubTimestamp extends SproutSub[OffsetDateTime] {
  def now[F[_]](implicit F: Applicative[F], t: Time[F]): F[Type] = t.now.map(this.newType)
}
