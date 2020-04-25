package com.example.chapterone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.example.chapterone.DumyData.DummyContent
import com.example.chapterone.fragments.SkillDetail
import com.example.chapterone.fragments.SkillList
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Array.newInstance

class SkillsActivity : AppCompatActivity(), SkillList.OnListFragmentInteractionListener {

    private var dualPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skills)

        if (intent.extras != null && intent.extras!!.containsKey(INTENT_CURRENT_MENU_CONST)){
            val selected_item_id = intent.extras!![INTENT_CURRENT_MENU_CONST]
            bottom_nav.selectedItemId = selected_item_id as Int
        }

        val detailsFrame: View? = this.findViewById(R.id.skillDetail)
        dualPane = detailsFrame?.visibility == View.VISIBLE

        if (dualPane) {
            showDetails(DummyContent.ITEMS[0])
        }

        setOnNavigationItemSelectedListeners()
    }

    private fun setOnNavigationItemSelectedListeners(){
        bottom_nav.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.menu_item_contact && it.itemId != bottom_nav.selectedItemId){
                val intent = Intent(this, ContactActivity::class.java)
                intent.putExtra(INTENT_CURRENT_MENU_CONST, it.itemId)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            } else if (it.itemId == R.id.menu_item_about && it.itemId != bottom_nav.selectedItemId){
                val intent = Intent(this, AboutActivity::class.java)
                intent.putExtra(INTENT_CURRENT_MENU_CONST, it.itemId)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            return@setOnNavigationItemSelectedListener true
        }

    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        showDetails(item)
    }

    private fun showDetails(item: DummyContent.DummyItem?) {
        if (item == null){
            return
        }
        if (dualPane) {
            var details = supportFragmentManager.findFragmentById(R.id.skillDetail) as? SkillDetail
            if (details?.showId != item.id) {
                // Make new fragment to show this selection.
                details = SkillDetail.newInstance(item.id)

                supportFragmentManager.beginTransaction()?.apply {
                    if (item.id == "0") {
                        replace(R.id.skillDetail, details)
                    } else {
                        replace(R.id.skillDetail, details)
                    }

                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    commit()
                }
            }

        } else {
        }
    }
}
