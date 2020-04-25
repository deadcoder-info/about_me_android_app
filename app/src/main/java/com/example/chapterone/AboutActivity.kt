package com.example.chapterone

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.extras != null && intent.extras!!.containsKey(INTENT_CURRENT_MENU_CONST)){
            val selected_item_id = intent.extras!![INTENT_CURRENT_MENU_CONST]
            bottom_nav.selectedItemId = selected_item_id as Int
        }

        setOnNavigationItemSelectedListeners()

        setRandomShortcut()
    }

    private fun setOnNavigationItemSelectedListeners(){
        bottom_nav.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.menu_item_contact && it.itemId != bottom_nav.selectedItemId){
                val intent = Intent(this, ContactActivity::class.java)
                intent.putExtra(INTENT_CURRENT_MENU_CONST, it.itemId)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            } else if (it.itemId == R.id.menu_item_skills && it.itemId != bottom_nav.selectedItemId){
                val intent = Intent(this, SkillsActivity::class.java)
                intent.putExtra(INTENT_CURRENT_MENU_CONST, it.itemId)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            return@setOnNavigationItemSelectedListener true
        }


    }

    private fun setRandomShortcut(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1){
            val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)

            val rand = (0..5).random()

            val shortcut = ShortcutInfo.Builder(this, "RandId")
                .setShortLabel(rand.toString())
                .setLongLabel(rand.toString())
                .setIcon(Icon.createWithResource(this, R.drawable.random))
                .setIntent(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.mysite.example.com/")))
                .build()

            shortcutManager!!.dynamicShortcuts = Arrays.asList(shortcut)
        }

    }
}
