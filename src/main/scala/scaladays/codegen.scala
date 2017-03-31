package scaladays

import java.io.File
import scala.collection.immutable.Seq
import scala.meta._
import scala.annotation.StaticAnnotation
// import org.json4s._
// import org.json4s.native.JsonMethods._

class codegen extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    // implicit val formats = DefaultFormats

    defn
  }
}
