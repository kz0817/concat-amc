import java.io._

object ClacGaps {

  val src = List(
    Map("title" -> "disc1.1",
        "video" -> "01:45:15.309", "audio" -> "01:45:14.705"),
    Map("title" -> "disc1.2",
        "video" -> "00:21:28.287", "audio" -> "00:21:26.820"),
    Map("title" -> "disc2.1",
        "video" -> "01:42:51.165", "audio" -> "01:42:50.165"),
    Map("title" -> "disc2.2",
        "video" -> "00:24:53.492", "audio" -> "00:24:53.490"),
    Map("title" -> "disc3.1",
        "video" -> "01:30:37.432", "audio" -> "01:30:36.735"),
    Map("title" -> "disc3.2",
        "video" -> "00:15:34.934", "audio" -> "00:15:34.935")
  );

  val SamplingRate = 48 * 1000.0;
  val BitsPerSample = 24;
  val NumChannels = 2;


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

  def createRawPCM(title: String, count: Int) {
    printf("count: %d%n", count)
    val totalBytes = count * BitsPerSample/8 * NumChannels;
    val filename = title + ".tail-pad." + SamplingRate.toInt +
                   "hz_" + BitsPerSample + "bit_" + NumChannels + "ch.pcm"
    val file = new BufferedOutputStream(new FileOutputStream(filename))
    file.write((1 to totalBytes) map { x => 0.byteValue } toArray)
    file.close
  }

  def calcDiffs() {
    val videoLenList = src map { _("video") } map sec
    val audioLenList = src map { _("audio") } map sec
    val diffList = videoLenList zip(audioLenList) map { t => t._1 - t._2 }
    val samplesList = diffList map numSamples
    src map { _("title") } zip diffList zip samplesList foreach { x =>
      val count = x._2.toInt
      val title: String = x._1._1
      printf("title: %s, diff: %.6f, count: %.1f%n",
             title, x._1._2, x._2)
      createRawPCM(title, count)
    }
  }

  def main(args: Array[String]): Unit = {
    showTotalTime()
    calcDiffs()
  }
}
