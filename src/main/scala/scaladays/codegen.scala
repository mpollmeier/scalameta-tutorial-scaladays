package scaladays

import java.io.File
import scala.collection.immutable.Seq
import scala.meta._
import scala.annotation.StaticAnnotation
import org.json4s._
import org.json4s.native.JsonMethods._

class codegen extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    implicit val formats = DefaultFormats

    val json = parse(FileInput(new File("domain.json")))
    def classes = json.extract[Map[String, List[(String, String)]]].map {
      case (name, members) => 
        val membersTerms: Seq[Term.Param] = members.map {
          case (name, domainType) =>
            val paramName = Term.Name(name)
            val tpe = toScalaType(domainType)
            param"$paramName: $tpe"
        }
        val tName = Type.Name(name.capitalize)
        q"case class $tName(..$membersTerms)"
    }.toList

    def toScalaType(domainType: String): Type = domainType match {
      case "int" => t"Int"
      case "string" => t"String"
    }

    defn match {
      case obj: Defn.Object =>
        val ret = q"object ${obj.name} {..$classes}"
        println(ret)
        ret
    }
  }
}
