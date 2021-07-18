package academy.learnprogramming.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //lateinit is used to subvert the null data type we would otherwise use to init the widgets in
    //the onCreate function. lateinit can only be used for vars
    private lateinit var result: EditText
    private lateinit var newNumber: EditText

    //or you can use lazy delegation, which can only be used for vals
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    //Variables to hold our operands and calculation
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        //There are two different ways of assigning button widgets:
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttonDot)

        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            //not all widgets have a text property so we need to specify the
            //widget we are getting the text from
            newNumber.append(b.text)
        }

        //Setting up buttons
        //Can optimize this by adding all buttons to an array and calling each button's
        //onClickListener. Should do this ESP if we are implementing a full keyboard
        /*
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)
        */

        //Used listOf instead of arrayOf because list is immutable whereas an array is
        val valButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDot)
        for (b in valButtons) b.setOnClickListener(listener)

        //Setup onClickListener for operation buttons
        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            val value = newNumber.text.toString()
            if (value.isNotEmpty()) { //If value is not "" - change operation on calc
                performOperand(value, op)
            }
            //Regardless if value is "" or not,
            pendingOperation = op
            displayOperation.text = pendingOperation
        }

        val opButtons = listOf(buttonEquals, buttonDivide, buttonMultiply, buttonMinus, buttonPlus)
        for (op in opButtons) op.setOnClickListener(opListener)
    }

    private fun performOperand(value: String, operation: String) {
        displayOperation.text = operation
        
    }

}