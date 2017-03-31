package meta

import java.io.File
import org.json4s._
import org.json4s.native.JsonMethods._
import scala.annotation.StaticAnnotation
import scala.collection.immutable.Seq
import scala.meta._

class codegen extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    implicit val formats = DefaultFormats

    defn
  }
}
