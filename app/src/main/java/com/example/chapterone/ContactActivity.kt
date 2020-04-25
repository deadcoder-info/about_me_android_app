package com.example.chapterone

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottom_nav

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)


        if (intent.extras != null && intent.extras!!.containsKey(INTENT_CURRENT_MENU_CONST)){
            val selected_item_id = intent.extras!![INTENT_CURRENT_MENU_CONST]
            bottom_nav.selectedItemId = selected_item_id as Int
        }

        setOnNavigationItemSelectedListeners()

        setOnClickListeners()
    }

    private fun setOnNavigationItemSelectedListeners(){
        bottom_nav.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.menu_item_about && it.itemId != bottom_nav.selectedItemId){
                val intent = Intent(this, AboutActivity::class.java)
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

    private fun setOnClickListeners(){
        btnCall.setOnClickListener {
            callMe()
        }

        btnEmail.setOnClickListener {
            emailMe()
        }

        btnGoToSite.setOnClickListener {
            openMySite()
        }

        btnSendContact.setOnClickListener {
            sendMyInfo()
        }
    }

    private fun callMe(){
        val callIntent: Intent = Uri.parse("tel:555555555").let { number ->
            Intent(Intent.ACTION_DIAL, number)
        }
        val activities: List<ResolveInfo> = packageManager.queryIntentActivities(callIntent, 0)
        val isIntentSafe: Boolean = activities.isNotEmpty()

        if (isIntentSafe){
            startActivity(callIntent)
        }
    }

    private fun emailMe(){
        val mailIntent = Intent(Intent.ACTION_SEND)
        mailIntent.apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("bshadmehr98@gmail.com")) // recipients
            putExtra(Intent.EXTRA_SUBJECT, "Email From About Me App")
            putExtra(Intent.EXTRA_TEXT, "Edit This...")
        }
        val activities: List<ResolveInfo> = packageManager.queryIntentActivities(mailIntent, 0)
        val isIntentSafe: Boolean = activities.isNotEmpty()

        if (isIntentSafe){
            startActivity(mailIntent)
        }
    }

    private fun openMySite(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("http://www.android.com")
        }

        val shareIntent = Intent.createChooser(sendIntent, "title")
        if (intent.resolveActivity(packageManager) != null){
            startActivity(shareIntent)
        }
    }

    private fun sendMyInfo(){
        val info = "Hi there \nYou can simply contact me via my email:\nbshadmehr98@gmail.com"
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, info)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Send My Contact")
        if (intent.resolveActivity(packageManager) != null){
            startActivity(shareIntent)
        }
    }
}
