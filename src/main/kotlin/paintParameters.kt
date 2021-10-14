import org.jetbrains.skija.*
import org.jetbrains.skija.Font
import org.jetbrains.skija.Paint

val typeface = Typeface.makeFromFile("fonts/JetBrainsMono-Regular.ttf")

val paintBlack = Paint().apply {
    color = 0xff000000.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}
val paintFirst = Paint().apply {
    color = 0xff90D0CD.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}

val paintSecond = Paint().apply {
    color = 0xffFF484C.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}
val paintThird = Paint().apply {
    color = 0xff644D90.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}
val paintForth = Paint().apply {
    color = 0xffFFA085.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}
val paintFifth = Paint().apply {
    color = 0xff365C6F.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}
val paintSixth = Paint().apply {
    color = 0xff1CA289.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}

val basePaints = listOf(paintFirst, paintSecond, paintThird, paintForth, paintFifth, paintSixth)