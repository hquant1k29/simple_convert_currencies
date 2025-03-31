package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var proportion: TextView
    private lateinit var unit1: TextView
    private lateinit var unit2: TextView
    private lateinit var spCurrency1: Spinner
    private lateinit var spCurrency2: Spinner
    private lateinit var textWatcher1: TextWatcher
    private lateinit var textWatcher2: TextWatcher

    private val exchangeRates = mapOf(
        "USD" to 1.0,
        "VND" to 23500.0,
        "EUR" to 0.92,
        "GBP" to 0.78,
        "JPY" to 150.0
    )
    private val symbol = mapOf(
        "USD" to "$",
        "VND" to "đ",
        "EUR" to "€",
        "GBP" to "£",
        "JPY" to "¥"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        proportion = findViewById(R.id.test)
        unit1 = findViewById(R.id.unit1)
        unit2 = findViewById(R.id.unit2)

        spCurrency1 = findViewById(R.id.spCurrency1)
        spCurrency2 = findViewById(R.id.spCurrency2)
        Log.v("TAG","TEXT1 $editText1")
        val currencies = listOf("USD","VND","EUR","GBP","JPY")

        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            currencies
        )
        spCurrency1.adapter = adapter
        spCurrency2.adapter = adapter

        spCurrency2.setSelection(currencies.indexOf("VND"))

        textWatcher1 = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                convertCurrency(true)
                if (editText1.text.isNullOrEmpty()) {
                    editText2.setText("0.0")
                }
            }
        }
        editText1.addTextChangedListener(textWatcher1)
        textWatcher2 = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                convertCurrency(false)
                if (editText2.text.isNullOrEmpty()) {
                    editText1.setText("0.0")
                }
            }
        }
        editText2.addTextChangedListener(textWatcher2)
        spCurrency1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertCurrency(true)
                unit1.text = symbol[spCurrency1.selectedItem]
                changePro()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spCurrency2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertCurrency(false)
                unit2.text = symbol[spCurrency2.selectedItem]
                changePro()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }
    @SuppressLint("DefaultLocale")
    private fun convertCurrency(check: Boolean) {
        if(check){
            val amount = editText1.text.toString().toDoubleOrNull() ?: return
            val fromCurrency = spCurrency1.selectedItem.toString()
            val toCurrency = spCurrency2.selectedItem.toString()
            val rateFrom = exchangeRates[fromCurrency] ?: 1.0
            val rateTo = exchangeRates[toCurrency] ?: 1.0
            val convertedAmount = amount * (rateTo / rateFrom)
            editText2.removeTextChangedListener(textWatcher2)
            editText2.setText(String.format("%.2f", convertedAmount))
            editText2.addTextChangedListener(textWatcher2)
        } else {
            val amount = editText2.text.toString().toDoubleOrNull() ?: return
            val fromCurrency = spCurrency2.selectedItem.toString()
            val toCurrency = spCurrency1.selectedItem.toString()
            val rateFrom = exchangeRates[fromCurrency] ?: 1.0
            val rateTo = exchangeRates[toCurrency] ?: 1.0
            val convertedAmount = amount * (rateTo / rateFrom)
            editText1.removeTextChangedListener(textWatcher1)
            editText1.setText(String.format("%.2f", convertedAmount))
            editText1.addTextChangedListener(textWatcher1)
        }
    }
    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun changePro(){
        val fromCurrency = spCurrency2.selectedItem.toString()
        val toCurrency = spCurrency1.selectedItem.toString()
        val rateFrom = exchangeRates[fromCurrency] ?: 1.0
        val rateTo = exchangeRates[toCurrency] ?: 1.0

        if(rateFrom > rateTo){
            val exchange = 1/rateTo * rateFrom
            val modi = String.format("%.2f", exchange)
            proportion.setText("1 " + toCurrency + " = " + modi + " " + fromCurrency)
        }else {
            val exchange = 1/rateFrom * rateTo
            val modi = String.format("%.2f", exchange)
            proportion.setText("1 " + fromCurrency + " = " + modi + " " + toCurrency)
        }
    }
}