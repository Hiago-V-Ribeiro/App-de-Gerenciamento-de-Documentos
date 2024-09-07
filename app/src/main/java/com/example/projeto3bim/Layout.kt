package com.example.projeto3bim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Layout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val btn: Button = findViewById(R.id.btnLev)
        val btn2: Button = findViewById(R.id.btnupdocs)
        val btn3: Button = findViewById(R.id.btnDocsLev)

        btn.setOnClickListener {
            val intent = Intent(this@Layout, Update::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val intent = Intent(this@Layout, UparImg::class.java)
            startActivity(intent)
        }

        btn3.setOnClickListener {
            val intent = Intent(this@Layout, Docs::class.java)
            startActivity(intent)
        }

    }
}