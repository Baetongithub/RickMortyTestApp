package com.example.rickmortytestapp.presentation.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(txt: String) = Toast.makeText(this.context, txt, Toast.LENGTH_SHORT).show()