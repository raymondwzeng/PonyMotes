//package com.dinsfire.ponymotes
//
//import android.content.Context
//
//class SquareImageView : ImageView {
//    constructor(context: Context?) : super(context) {}
//    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
//    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
//        context,
//        attrs,
//        defStyle
//    ) {
//    }
//
//    protected fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth())
//    }
//}