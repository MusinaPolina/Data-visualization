import org.jetbrains.skija.*
import org.jetbrains.skija.Font
import org.jetbrains.skija.Paint

val typeface = Typeface.makeFromFile("fonts/JetBrainsMono-Regular.ttf")
val typefaceCoefficient = 0.6f

fun stringCenterShift(name: String, fontSize: Float): Float {
    return name.length.toFloat() * 0.5f * fontSize * typefaceCoefficient
}

val paintBlack = Paint().apply {
    color = 0xff000000.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}
val paintGrey = Paint().apply {
    color = 0xffD5D5D5.toInt()
    mode = PaintMode.FILL
    strokeWidth = 1f
}

val paintFirst = Paint().apply {
    color = 0xff90D0CD.toInt()
    mode = PaintMode.FILL
    strokeWidth = 2f
}

val paintSecond = Paint().apply {
    color = 0xffFF484C.toInt()
    mode = PaintMode.FILL
    strokeWidth = 2f
}

val paintThird = Paint().apply {
    color = 0xff644D90.toInt()
    mode = PaintMode.FILL
    strokeWidth = 2f
}

val paintForth = Paint().apply {
    color = 0xffFFA085.toInt()
    mode = PaintMode.FILL
    strokeWidth = 2f
}

val paintFifth = Paint().apply {
    color = 0xff365C6F.toInt()
    mode = PaintMode.FILL
    strokeWidth = 2f
}

val paintSixth = Paint().apply {
    color = 0xff1CA289.toInt()
    mode = PaintMode.FILL
    strokeWidth = 2f
}

val basePaints = listOf(paintFirst, paintSecond, paintThird, paintForth, paintFifth, paintSixth)