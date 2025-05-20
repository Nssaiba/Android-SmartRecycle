package com.smartrecycle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import java.nio.ByteBuffer
import java.nio.ByteOrder

class WasteClassifier(context: Context) {
    private val interpreter: Interpreter
    private val labels: List<String>
    private val imageSize = 224  // selon ton modèle

    init {
        val model = FileUtil.loadMappedFile(context, "waste_classification_model1.tflite")
        interpreter = Interpreter(model)
        labels = FileUtil.loadLabels(context, "labels.txt")
    }

    fun classify(bitmap: Bitmap): Pair<String, Float> {
        val resized = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, true)
        val input = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3).apply {
            order(ByteOrder.nativeOrder())
        }

        for (y in 0 until imageSize) {
            for (x in 0 until imageSize) {
                val pixel = resized.getPixel(x, y)
                input.putFloat(Color.red(pixel) / 255f)
                input.putFloat(Color.green(pixel) / 255f)
                input.putFloat(Color.blue(pixel) / 255f)
            }
        }

        val output = Array(1) { FloatArray(labels.size) }
        interpreter.run(input, output)

        val maxIndex = output[0].indices.maxByOrNull { output[0][it] } ?: -1
        return labels[maxIndex] to output[0][maxIndex]
    }
}
