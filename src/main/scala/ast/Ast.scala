package ast

sealed trait Ast
case class SelectStatement(select: Select, from: From, orderBy: OrderBy) extends Ast

case class Select(columns: Seq[Column])
case class From(tables: Seq[Table])
case class OrderBy(orders: Seq[Order])

case class Table(name: String)
case class Column(name: String)
case class Order(column: Column, ordering: Ordering)

sealed trait Ordering
case object Asc extends Ordering
case object Desc extends Ordering