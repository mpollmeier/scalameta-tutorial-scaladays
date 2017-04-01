package usage

import scaladays._

// @mappable trait A

// @mappable class B {
//   @mappable val bVal = "hi there"
// }

@mappable case class Customer(i: Int, s: String)

// @mappable object D {
//   @mappable type Inner = Int
//   @mappable val inner: Inner = 5
// }

object Usage extends App {
  val c = Customer(5, "a string")
  println(Customer.toMap.apply(c))
}
