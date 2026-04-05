import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class StarfieldView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint().apply { color = Color.WHITE }
    private val stars = MutableList(150) {
        floatArrayOf(Random.nextFloat() * width, Random.nextFloat() * height, Random.nextFloat() * 3)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.BLACK)

        stars.forEach { star ->
            // Move stars downward
            star[1] += star[2]
            if (star[1] > height) star[1] = 0f
            canvas.drawCircle(star[0], star[1], star[2], paint)
        }

        // Animate planets (simple colored circles)
        paint.color = Color.CYAN
        canvas.drawCircle(width / 3f, height / 3f, 40f, paint)

        paint.color = Color.MAGENTA
        canvas.drawCircle(width * 2f / 3f, height / 2f, 60f, paint)

        invalidate() // redraw continuously
    }
}