import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.RelativeLayout

/**
 * Created by docouto on 4/9/18.
 */

/**
 *  SquareLayout is a subclass of RelativeLayout that creates a square layout.
 *  it overrides the onMeasure method to set a square measure for the layout, based on the width specified in the widthMeasureSpec.
 */
class SquareLayout : RelativeLayout {

    /**
     * Creates a new SquareLayout instance.
     * @param context the context to use.
     */
    constructor(context: Context) : super(context)

    /**
     * Creates a new SquareLayout instance with a set of attributes.
     * @param context the context to use.
     * @param attrs the attributes to use.
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    /**
     * Creates a new SquareLayout instance with a set of attributes and a default attribute style.
     * @param context the context to use.
     * @param attrs the attributes to use.
     * @param defStyleAttr the default attribute style to use.
     */
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
    * Creates a new SquareLayout instance with a set of attributes, a default attribute style and a default style resource.
    * @param context the context to use.
    * @param attrs the attributes to use.
    * @param defStyleAttr the default attribute style
    */

override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Set a square layout.
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}