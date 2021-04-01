
package com.example.android.customkeyboardapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.customkeyboardapp.loaders.GlideImageLoader
import com.example.android.customkeyboardapp.picker.CustomPhotoPicker
import com.example.android.customkeyboardapp.picker.PhotoPickerFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btShowImagePicker.setOnClickListener {

            CustomPhotoPicker().init(
                loader = GlideImageLoader(),
                authority = "com.example.android.customkeyboardapp.fileprovider"
            )

            PhotoPickerFragment.newInstance(
                multiple = true,
                allowCamera = true,
                maxSelection = 5,
                theme = R.style.CustomDark
            ).show(supportFragmentManager, "picker")
        }


        /*val et = findViewById<EditText>(R.id.etvInput)
        val btChangeLanguage = findViewById<Button>(R.id.btChangeLanguage)
        var currLanguage = 0
        *//*et.imeHintLocales = (LocaleList(Locale("hi_IN")))*//*

        val hindiKeyboard1 = Keyboard(this, R.xml.hindi_keyboard_1)
        val hindiKeyboard2 = Keyboard(this, R.xml.hindi_keyboard_2)
        val gujaratiKeyboard1 = Keyboard(this, R.xml.gujarati_keyboard_1)
        val gujaratiKeyboard2 = Keyboard(this, R.xml.gujarati_keyboard_2)
        val keyboardView = findViewById<KeyboardView>(R.id.kbvInput)
        keyboardView.keyboard = hindiKeyboard1

        *//*
        Handle
        All
        Swipe
        Events
        !
        !
         *//*
        keyboardView.setOnKeyboardActionListener(object :
            KeyboardView.OnKeyboardActionListener {
            override fun onPress(p0: Int) {
            }

            override fun onRelease(p0: Int) {

            }

            override fun onKey(primaryCode: Int, keyCodes: IntArray?) {

                when (primaryCode) {
                    66 -> {
                        val eventTime: Long = System.currentTimeMillis()
                        val event = KeyEvent(
                            eventTime, eventTime, KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
                            KeyEvent.FLAG_SOFT_KEYBOARD, KeyEvent.FLAG_KEEP_TOUCH_MODE
                        )
                        this@MainActivity.dispatchKeyEvent(event)
                    }
                    67 -> {
                        val eventTime: Long = System.currentTimeMillis()
                        val event = KeyEvent(
                            eventTime, eventTime, KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
                            KeyEvent.FLAG_SOFT_KEYBOARD, KeyEvent.FLAG_KEEP_TOUCH_MODE
                        )
                        this@MainActivity.dispatchKeyEvent(event)
                    }
                    Keyboard.KEYCODE_DELETE -> {
                        et.setText("")
                    }
                    -106 -> {
                        keyboardView.keyboard = hindiKeyboard2
                    }
                    -107 -> {
                        keyboardView.keyboard = hindiKeyboard1
                    }
                    -108 -> {
                        keyboardView.keyboard = gujaratiKeyboard2
                    }
                    -109 -> {
                        keyboardView.keyboard = gujaratiKeyboard1
                    }
                    else -> {
                        val newText = et.text.toString() + primaryCode.toChar()
                        et.setText(newText)
                    }
                }
            }

            override fun onText(p0: CharSequence?) {
                val cursorPosition: Int = et.selectionEnd
                val previousText: String = et.text.toString()
                val before: String
                val after: String
                if (cursorPosition < previousText.length) {
                    before = previousText.substring(0, cursorPosition)
                    after = previousText.substring(cursorPosition)
                } else {
                    before = previousText
                    after = ""
                }
                et.setText(before + p0.toString() + after)
                et.setSelection(cursorPosition + 1)
            }

            override fun swipeLeft() {
                TODO("Not yet implemented")
            }

            override fun swipeRight() {
                TODO("Not yet implemented")
            }

            override fun swipeDown() {
                TODO("Not yet implemented")
            }

            override fun swipeUp() {
                TODO("Not yet implemented")
            }

        })

        et.showSoftInputOnFocus = false
        et.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            view.performClick()
            keyboardView.visibility = View.VISIBLE
            keyboardView.isEnabled = true
            view.clearFocus()
            return@OnTouchListener false
        })

        *//*btChangeLanguage.setOnClickListener {
            if ( currLanguage == 0){
               keyboardView.keyboard = gujaratiKeyboard1
                currLanguage = 1
            } else {
                keyboardView.keyboard = hindiKeyboard1
                currLanguage = 0
            }
        }*//*
*/
        /*val userLocation = LocationFinder()
        val locationEnabled: Boolean = userLocation.getLocation(this, object: LocationFinder.LocationResult(){
            override fun gotLocation(location: Location?) {
                findViewById<TextView>(R.id.tvLocation).text = "$location.latitude $location.longitude"
            }

        })

        if (!locationEnabled) {
            findViewById<TextView>(R.id.tvLocation).text = "location not enabled"
        }*/


    }
}
/*
        setContentView(R.layout.activity_view_pager)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        actionBar?.customView = toolbar
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

    }

    class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            return Fragment(R.layout.activity_main)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return (position + 1).toString() + " Tab"
        }
    }
}*/
