package usage

import meta._

@mappable case class Customer(i: Int, s: String)

object Usage extends App {
  val c = Customer(5, "a string")
  println(Customer.toMap(c))
}
