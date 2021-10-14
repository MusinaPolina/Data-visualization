fun normalizationLine(values: List<List<Float>>): ValuesRange {
    val all: List<Float> = values.flatten().sorted().distinct()
    require(all.isNotEmpty())
    val step: Int = Math.max(1, ((all.last() - all.first()) / Math.max(1, (all.size - 1))).toInt())
    val start = kotlin.math.floor(all.first() - step).toInt()
    return ValuesRange(start, step, (kotlin.math.ceil((all.last() - start) / step)).toInt() + 1)
}

fun normalizationColumn(values: List<List<Float>>): ValuesRange {
    val all: List<Float> = values.flatten().sorted().distinct()
    require(all.isNotEmpty())
    val step: Int = Math.max(1, ((all.last() - all.first()) / Math.max(1, (all.size - 1))).toInt())
    return ValuesRange(0, step, (kotlin.math.ceil(all.last() / step)).toInt() + 1)
}