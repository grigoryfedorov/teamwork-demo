package com.grigoryfedorov.teamwork

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.grigoryfedorov.teamwork.ui.projectslist.ProjectsListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, ProjectsListActivity::class.java))
        finish()
    }
}
