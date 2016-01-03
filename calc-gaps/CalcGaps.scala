object ClacGaps {

  val src = List(
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

  val SamplingRate = 48 * 1000.0;


  def sec(time: String): Double = {
    time split(":") zip(List(3600, 60, 1)) map {
      t => t._1.toDouble * t._2 } sum
  }

  def showTotalTime() {
    val videoLen = (src map { _("video") } map sec sum)
    val audioLen = (src map { _("audio") } map sec sum)
    printf("Total: video: %.6f, audio: %.6f, diff: %.6f (sec)%n",
           videoLen, audioLen, (videoLen - audioLen))
  }

  def numSamples(time: Double): Double = {
    SamplingRate * time
  }

  def showDiffs() {
    val videoLenList = src map { _("video") } map sec
    val audioLenList = src map { _("audio") } map sec
    val diffList = videoLenList zip(audioLenList) map { t => t._1 - t._2 }
    val samplesList = diffList map numSamples
    src map { _("title") } zip diffList zip samplesList foreach { x =>
      printf("title: %s, diff: %.6f, count: %.1f%n",
             x._1._1, x._1._2, x._2)
    }
  }

  def main(args: Array[String]): Unit = {
    showTotalTime()
    showDiffs()
  }
}
