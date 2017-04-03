package usage

import scaladays._

// @mappable trait A

// @mappable class B {
//   @mappable val bVal = "hi there"
// }

// @mappable case class Customer(i: Int, s: String)

// @mappable object D {
//   @mappable type Inner = Int
//   @mappable val inner: Inner = 5
// }

// class E(@mappable i: Int)

@codegen object MyDomain

object Usage extends App {
  import MyDomain._
  val c = Customer(name = "Michael", yearOfBirth = 1983)
  println(c)
}
