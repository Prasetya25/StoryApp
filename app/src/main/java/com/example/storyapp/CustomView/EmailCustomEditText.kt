package com.example.storyapp.CustomView

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText

class EmailCustomEditText : AppCompatEditText {

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
//        drawableIcon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_email_24) as Drawable
//        setEditTextDrawables(drawableIcon)
        compoundDrawablePadding = 24
        setHint("Email")
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val isValid = Patterns.EMAIL_ADDRESS.matcher(p0.toString()).matches()
                error = if (!isValid && !p0.isNullOrEmpty()) "Invalid Email" else null
//                if (!p0.isNullOrEmpty() && !Patterns.EMAIL_ADDRESS.matcher(p0).matches())
//                    error = "Invalid Email"
//                if (!p0.isNullOrEmpty() && !isValid) error = "Invalid Email"
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