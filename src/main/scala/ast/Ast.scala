package ast

sealed trait Ast
case class Select(columns: Seq[Column]) extends Ast
case class From(tables: Seq[Table])
case class OrderBy(orders: Seq[Order])

case class Table()
case class Column()
case class Order(column: Column, ordering: Ordering)

sealed trait Ordering
case object Asc extends Ordering
case object Desc extends Ordering