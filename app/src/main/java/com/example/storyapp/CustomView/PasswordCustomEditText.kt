package com.example.storyapp.CustomView

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class PasswordCustomEditText : AppCompatEditText {
//    private lateinit var drawableIcon: Drawable

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
//        drawableIcon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_lock_24) as Drawable
//        setEditTextDrawables(drawableIcon)
        compoundDrawablePadding = 24
        setHint("Password")
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                error = if (!p0.isNullOrEmpty() && p0.length < 6) "Password kurang dari 6 karakter" else null
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun setEditTextDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText:Drawable? = null,
        endOfTheText:Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(startOfTheText, topOfTheText, endOfTheText, bottomOfTheText)
    }
}