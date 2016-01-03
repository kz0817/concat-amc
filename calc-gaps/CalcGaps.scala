object ClacGaps {

  val src = Array(
    Map("title" -> "disc1.1",
        "video" -> "01:45:15.309", "audio" -> "01:45:14.705"),
    Map("title" -> "disc1.2",
        "video" -> "00:21:28.287", "audio" -> "00:21:26.815"),
    Map("title" -> "disc2.1",
        "video" -> "01:42:51.165", "audio" -> "01:42:51.160"),
    Map("title" -> "disc2.2",
        "video" -> "00:24:53.492", "audio" -> "00:24:53.485"),
    Map("title" -> "disc3.1",
        "video" -> "01:30:37.432", "audio" -> "01:30:36.730"),
    Map("title" -> "disc3.2",
        "video" -> "00:15:34.934", "audio" -> "00:15:34.930")
  );

  def main(args: Array[String]): Unit = {
    src foreach println
  }
}
