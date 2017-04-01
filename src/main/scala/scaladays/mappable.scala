package scaladays

import scala.annotation.StaticAnnotation
import scala.collection.immutable.Seq
import scala.meta._

trait ToMap[A] {
  def apply(a: A): Map[String, Any]
}
trait FromMap[A] {
  def apply(keyValues: Map[String, Any]): A
}

class mappable extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    defn match {
      case clazz: Defn.Class =>
        val typeName = clazz.name
        val termName = Term.Name(typeName.value)
        val params = clazz.ctor.paramss.flatten

        val toMapKeyValues = params.map { param =>
          val paramName: String = param.name.value
          q"($paramName -> instance.${Term.Name(paramName)})"
        }

        val fromMapCtorArgs = params.map {param =>
          val paramName: String = param.name.value
          val paramNameTerm = Term.Name(paramName)
          val tpe = param.decltpe match {
            case Some(tpe: Type) => tpe
            case _ => ???
          }
          q""" $paramNameTerm = keyValues($paramName).asInstanceOf[$tpe] """
        }

        val companion = q"""
          object $termName {
            implicit def toMap() = new _root_.scaladays.ToMap[$typeName] {
              override def apply(instance: $typeName) = scala.collection.immutable.Map(..$toMapKeyValues)
            }

            implicit def fromMap() = new _root_.scaladays.FromMap[$typeName] {
              def apply(keyValues: scala.collection.immutable.Map[String, Any]) = $termName(..$fromMapCtorArgs)
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
