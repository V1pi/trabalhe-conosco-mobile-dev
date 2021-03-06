package com.v1pi.picpay_teste

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.v1pi.picpay_teste.Controllers.CreateCreditCardController
import com.v1pi.picpay_teste.Domains.CreditCard
import com.v1pi.picpay_teste.Utils.MaskWatcher
import kotlinx.android.synthetic.main.activity_create_credit_card.*
import kotlinx.android.synthetic.main.activity_payment_method.*

class CreateCreditCardActivity : AppCompatActivity() {
    lateinit var controller : CreateCreditCardController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_credit_card)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val arrayAdapter = ArrayAdapter.createFromResource(this, R.array.credit_card_flags, R.layout.spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_credit_card_flag.adapter = arrayAdapter

        controller = CreateCreditCardController(this)

        edit_expiry_date.addTextChangedListener(MaskWatcher("##/##"))
        edit_invoice_code.addTextChangedListener(MaskWatcher("#####-###"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun register(view : View) {

        if(edit_number.text.isEmpty() || edit_cvv.text.isEmpty() || edit_expiry_date.text.isEmpty() ||
                edit_cvv.text.length != 3 || edit_number.text.length != 16 || edit_expiry_date.text.length != 5) {
            Toast.makeText(this, getString(R.string.no_fill_required_fields), Toast.LENGTH_SHORT).show()

            return
        }

        // Não vi necessidade de adicionar os campos diferentes desses no modelo do Cartão de Crédito já que não são utilizados
        val creditCard = CreditCard(0, edit_number.text.toString(), edit_cvv.text.toString().toInt(), edit_expiry_date.text.toString())
        controller.insertCreditCard(creditCard)
    }

    fun clearFormFields() {
        edit_cvv.text.clear()
        edit_expiry_date.text.clear()
        edit_invoice_code.text.clear()
        edit_name.text.clear()
        edit_number.text.clear()
    }

}