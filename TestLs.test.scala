//> using lib "org.scalameta::munit::0.7.27"

import scala.util.Properties

class TestsLs extends munit.FunSuite {
  test("ls") {
    // prepare test directory
    val tempDir = os.temp.dir()
    // create files
    val expectedFiles = Seq("Ls", "Hello").map(tempDir / _)
    expectedFiles.foreach(os.write(_, "Hello"))

    // check
    val scalaCLILauncher =
      if (Properties.isWin) "scala-cli.bat" else "scala-cli"
    val foundFiles =
      os.proc(scalaCLILauncher, "Ls.scala", "--", tempDir)
        .call()
        .out
        .text()
        .trim

    expectedFiles.map(_.toString).foreach { file =>
      assert(foundFiles.contains(file))
    }
  }
}
