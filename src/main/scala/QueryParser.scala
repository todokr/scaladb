import scala.collection.JavaConverters._
import scala.util.Try

import net.sf.jsqlparser.parser.CCJSqlParserUtil
import net.sf.jsqlparser.JSQLParserException
import net.sf.jsqlparser.statement.select.{Select => JSQLSelect}
import net.sf.jsqlparser.util.TablesNamesFinder

import ast._

object QueryParser {

  def parse(query: String): Either[Throwable, Ast] =
    Try(CCJSqlParserUtil.parse(query))
      .recover { case e: JSQLParserException => throw new QueryParseException(e.getMessage) }.toEither
      .flatMap {
        case s: JSQLSelect => AstBuilder.buildFrom(s)
        case _ => Left(new QueryParseException("unable to handle: " + query))
      }

  object AstBuilder {
    val tnf = new TablesNamesFinder

    def buildFrom(statement: JSQLSelect): Either[AstBuildException, Ast] = {
      val select = Select(Seq(Column("***"))) // TODO
      val from = From(tnf.getTableList(statement).asScala.map(Table.apply))
      val orderBy = OrderBy(Seq(Order(Column("***"), Asc))) // TODO
      Right(SelectStatement(select, from, orderBy))
    }

    class AstBuildException(msg: String) extends RuntimeException(msg)
  }

  class QueryParseException(msg: String) extends RuntimeException(msg)
}