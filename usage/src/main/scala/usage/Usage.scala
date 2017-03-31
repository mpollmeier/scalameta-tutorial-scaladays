package usage

import meta._

// @mappable trait A

// @mappable class B {
//   @mappable val bVal = "hi there"
// }

@mappable case class Customer(i: Int, s: String)

// @mappable object D {
//   @mappable type Inner = Int
//   @mappable val inner: Inner = 5
// }

// class E(@mappable i: Int)

object Usage extends App {
  println("hello scaladays")
}
