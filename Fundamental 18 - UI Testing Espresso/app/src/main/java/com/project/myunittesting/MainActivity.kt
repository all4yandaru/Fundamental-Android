package com.project.myunittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.project.myunittesting.model.CuboidModel
import com.project.myunittesting.model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // TODO 4: Implementasi kelas2 yang udah dibuat

    private lateinit var mainViewModel : MainViewModel

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCalculateVolume: Button
    private lateinit var btnCalculateSurfaceArea: Button
    private lateinit var btnCalculateCircumference: Button
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(CuboidModel())

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        tvResult = findViewById(R.id.tv_result)
        btnCalculateVolume = findViewById(R.id.btn_calculate_volume)
        btnCalculateCircumference = findViewById(R.id.btn_calculate_circumference)
        btnCalculateSurfaceArea = findViewById(R.id.btn_calculate_surface_area)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener(this)
        btnCalculateSurfaceArea.setOnClickListener(this)
        btnCalculateCircumference.setOnClickListener(this)
        btnCalculateVolume.setOnClickListener(this)

        // TODO Tambahan 2: kalo mau ambil dan set data tinggal gini, gaperlu findView.....
        // import pada activity atau fragment : import kotlinx.android.synthetic.main.activity_main.*
        // import pada adapter : kotlin.android.synthetic.main. + nama_xml + .view.*.
        text_name.text = "Liek Allyandaru"
        val name = text_name.text.toString().trim()
    }

    override fun onClick(v: View) {
        val length = edtLength.text.toString().trim()
        val height = edtHeight.text.toString().trim()
        val width = edtWidth.text.toString().trim()

        when {
            length.isEmpty() -> edtLength.error = "Field ini tidak boleh kosong"
            width.isEmpty() -> edtWidth.error = "Field ini tidak boleh kosong"
            height.isEmpty() -> edtHeight.error = "Field ini tidak boleh kosong"
            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()

                when (v.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        visible()
                    }
                    R.id.btn_calculate_circumference -> {
                        tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calculate_volume -> {
                        tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    // Kalo di klik nanti bakal muncul 3 buttonnya
    private fun visible(){
        btnCalculateCircumference.visibility = View.VISIBLE
        btnCalculateSurfaceArea.visibility = View.VISIBLE
        btnCalculateVolume.visibility = View.VISIBLE
        btnSave.visibility = View.GONE
    }

    private fun gone(){
        btnCalculateCircumference.visibility = View.GONE
        btnCalculateSurfaceArea.visibility = View.GONE
        btnCalculateVolume.visibility = View.GONE
        btnSave.visibility = View.VISIBLE
    }
}