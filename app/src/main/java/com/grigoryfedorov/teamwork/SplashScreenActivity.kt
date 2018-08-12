package com.grigoryfedorov.teamwork

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.grigoryfedorov.teamwork.ui.projects.ProjectsActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, ProjectsActivity::class.java))
        finish()
    }
}
