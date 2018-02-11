package ast

/**
 * Created by todokr on 18/02/11.
 */
sealed trait Ast
case class CreateDatabase() extends Ast
case class CreateTable() extends Ast
case class Insert() extends Ast
case class Select() extends Ast
