object ImplicitFactorial {
  trait Nat
  trait Succ[N<:Nat] <: Nat
  trait _0 <: Nat; object _0 extends _0
  type _1 = Succ[_0]; object _1 extends _1
  type _2 = Succ[_1]; object _2 extends _2
  type _3 = Succ[_2]; object _3 extends _3
  type _4 = Succ[_3]; object _4 extends _4
  type _5 = Succ[_4]; object _5 extends _5
  type _6 = Succ[_5]; object _6 extends _6

  final case class PlusN[-N<:Nat, M<:Nat, R<:Nat]()
  implicit def plus0[N<:Nat, M<:Nat] = PlusN[_0,M,M]
  implicit def plusN[N<:Nat, M<:Nat, R<:Nat](implicit n:PlusN[N,M,R]) = PlusN[Succ[N],M,Succ[R]]

  final case class TimesN[-N<:Nat, M<:Nat, R<:Nat]()
  implicit def times0[N<:Nat, M<:Nat] = TimesN[_0,M,_0]
  implicit def timesN[N<:Nat, M<:Nat, R<:Nat, X<:Nat](implicit n:TimesN[N,M,R], x:PlusN[M,R,X]) = TimesN[Succ[N],M,X]

  final case class FacN[-N<:Nat, R<:Nat]()
  implicit def fac0[N<:Nat] = FacN[_0,_1]
  implicit def facN[N<:Nat, R<:Nat, X<:Nat](implicit n: FacN[N,R], x: TimesN[Succ[N],R,X]) = FacN[Succ[N],X]

  def fac[N<:Nat, R<:Nat](n:N)(implicit p:FacN[N,R]) = p
}
