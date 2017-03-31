package meta

import scala.annotation.StaticAnnotation
import scala.collection.immutable.Seq
import scala.meta._

class mappable extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    defn match {
      case clazz: Defn.Class =>
        println(clazz)
        defn
    }
  }
}
