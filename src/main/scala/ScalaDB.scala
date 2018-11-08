import java.util.Scanner
import Console.{GREEN, RESET}

object ScalaDB extends App {

  val prompt = s"$GREEN:)$RESET "
  val scanner = new Scanner(System.in)
  print(prompt)

  while (scanner.hasNextLine) {
    val line = scanner.nextLine()
    val ast = QueryParser.parse(line)
    println(ast)
    print(prompt)
  }
}
