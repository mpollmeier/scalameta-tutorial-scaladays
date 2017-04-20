package meta

import scala.annotation.StaticAnnotation
import scala.collection.immutable.Seq
import scala.meta._

trait ToMap[A] {
  def apply(a: A): Map[String, Any]
}

class mappable extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    defn match {
      case clazz: Defn.Class =>
        val typeName = clazz.name
        val termName = Term.Name(typeName.value)
        val params = clazz.ctor.paramss.flatten

        val toMapKeyValues: Seq[Term.Arg] = params.map { param =>
          val paramName: String = param.name.value
          q"($paramName -> instance.${Term.Name(paramName)})"
        }

        val companion = q"""
          object $termName {
            import meta.ToMap
            def toMap = new ToMap[$typeName] {
              override def apply(instance: $typeName) = Map(..$toMapKeyValues)
            }
          }
        """

        val result = q"""
          $companion
          $clazz
        """

        println(result)
        result
    }
  }
}
