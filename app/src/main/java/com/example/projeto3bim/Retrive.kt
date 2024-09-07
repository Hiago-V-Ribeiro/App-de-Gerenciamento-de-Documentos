package com.example.projeto3bim

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Retrive : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var dbRef: DatabaseReference
    private val uploadedPDF: MutableList<putPDF> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retrive)

        listView = findViewById(R.id.ListView)
        retrievePDF()



        listView.setOnItemClickListener { _, _, position, _ ->
            val putPDF = uploadedPDF[position]

            val intent = Intent(Intent.ACTION_VIEW).apply {
                type = "application/pdf"
                data = Uri.parse(putPDF.url)
            }
            startActivity(intent)
        }
    }

    private fun retrievePDF() {
        dbRef = FirebaseDatabase.getInstance().getReference("uploadPDF")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                uploadedPDF.clear() // Limpar lista antes de adicionar novos dados
                for (ds in dataSnapshot.children) {
                    val putPDF = ds.getValue(com.example.projeto3bim.putPDF::class.java)
                    if (putPDF != null) {
                        uploadedPDF.add(putPDF)
                    }
                }

                val uploadsName = Array(uploadedPDF.size) { "" }
                for (i in uploadsName.indices) {
                    uploadsName[i] = uploadedPDF[i].name
                }

                val arrayAdapter = object : ArrayAdapter<String>(this@Retrive, android.R.layout.simple_list_item_1, uploadsName) {

                    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                        val view = super.getView(position, convertView, parent)
                        val textView = view.findViewById<TextView>(android.R.id.text1)
                        textView.setTextColor(Color.BLACK)
                        textView.textSize = 20f
                        return view
                    }
                }

                listView.adapter = arrayAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }
}
