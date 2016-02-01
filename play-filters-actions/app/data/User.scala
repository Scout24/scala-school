package data

/**
  * Created by matlloyd on 02/12/2015.
  */
case class User(id: Int, name: String, isAdmin: Boolean, pass: String)

object User {

  private val users: Map[Int, User] = Map(
    1 -> User(1, "Arif", false, "Abba"),
    2 -> User(2, "Marcus", false, "Bccb"),
    3 -> User(3, "Anna", true, "Cddc"),
    4 -> User(4, "Anika", true, "Edde")
  )

  def get(id: Int): Option[User] = users.get(id)

  def authenticate(username:String, pass: String): Boolean = true

}