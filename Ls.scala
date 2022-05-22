//> using scala "3.1.2"
//> using lib "com.lihaoyi::os-lib:0.8.0"

@main def hello(args: String*) =
  val path = args.headOption match
    case Some(p) => os.Path(p, os.pwd)
    case _       => os.pwd

  if (os.isDir(path)) println(os.list(path))
  else System.err.println("Expected directory path as input")
