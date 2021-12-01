package com.example.tyap4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

	var ruleEdit: EditText? = null
	var readEdit: EditText? = null
	var stackEdit: EditText? = null
	var nextEdit: EditText? = null
	var moveEdit: EditText? = null

	var text: TextView? = null
	//var but1: Button? = null

	var index = 0

	var arr: Array<Array<String>> = arrayOf()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val addButton: Button = findViewById(R.id.Add)
		val nextButton: Button = findViewById(R.id.NextButton)
		arr = Array(100, { Array(5, { "" }) })

		ruleEdit = findViewById(R.id.Rule)
		readEdit = findViewById(R.id.Read)
		stackEdit = findViewById(R.id.Stack)
		nextEdit = findViewById(R.id.NextRule)
		moveEdit = findViewById(R.id.Move)

		text = findViewById(R.id.textField)

		addButton.setOnClickListener {
			addRule()
			ruleEdit?.setText("")
			readEdit?.setText("")
			stackEdit?.setText("")
			nextEdit?.setText("")
			moveEdit?.setText("")
		}
		nextButton.setOnClickListener {
			start()
			//ruleE?.isVisible = false
			readEdit?.isVisible = false
			stackEdit?.isVisible = false
			nextEdit?.isVisible = false
			moveEdit?.isVisible = false
			addButton?.isVisible = false
		}
	}

	fun start() {
		val str = ruleEdit?.text.toString() + "e"
		var stack = "z"
		var state = "q0"
		var out = ""
		var out1 = ""
		var bool = false
		var symbol = false
		for (ch in str) {
			symbol = false
			for (i in 0..99) {
				if (arr[i][1].isNotEmpty()) {
					symbol = ch == arr[i][1][0]
					//out = out + arr[i][0] + " " + arr[i][1] + " " + arr[i][2] + " " + arr[i][3] + " " + arr[i][4] + '\n'
				}
				if (symbol) break
			}
			if (!symbol) break
		}

		if (str.isNotEmpty()) {
			for (ch in str) {
				out1 = out
				for (i in 0..10) {
					if (arr[i][0].isNotEmpty())
						if (state == arr[i][0] && ch.toString() == arr[i][1] && stack[0] == arr[i][2][0]
						) {
							state = arr[i][3]
							if (arr[i][4].length == 1 && arr[i][4][0] == 'e') {
								stack = stack.drop(1)
							} else if (arr[i][4].length == 1) {
								stack = stack
							} else {
								stack = arr[i][1] + stack
							}
							out = out + stack + '\n'
							break
						}
				}
				if (out == out1)
					bool = true
			}
		}
		if (state != "qf")
			bool = true
		when {
			!symbol -> text?.text = "Неизвестные символы в цепочке"
			bool    -> text?.text = out + "Цепочка невыполнима"
			else    -> text?.text = out + "Цепочка выполнима"
		}
	}

	fun addRule() {
		arr[index][0] = ruleEdit?.text.toString()
		arr[index][1] = readEdit?.text.toString()
		arr[index][2] = stackEdit?.text.toString()
		arr[index][3] = nextEdit?.text.toString()
		arr[index][4] = moveEdit?.text.toString()
//		arr[0][0] = "q0"
//		arr[0][1] = "a"
//		arr[0][2] = "z"
//		arr[0][3] = "q1"
//		arr[0][4] = "az"
//
//		arr[1][0] = "q1"
//		arr[1][1] = "b"
//		arr[1][2] = "a"
//		arr[1][3] = "q0"
//		arr[1][4] = "a"
//
//		arr[2][0] = "q0"
//		arr[2][1] = "a"
//		arr[2][2] = "a"
//		arr[2][3] = "q1"
//		arr[2][4] = "aa"
//
//		arr[3][0] = "q0"
//		arr[3][1] = "c"
//		arr[3][2] = "a"
//		arr[3][3] = "q3"
//		arr[3][4] = "e"
//
//		arr[4][0] = "q3"
//		arr[4][1] = "c"
//		arr[4][2] = "a"
//		arr[4][3] = "q3"
//		arr[4][4] = "e"
//
//		arr[5][0] = "q3"
//		arr[5][1] = "c"
//		arr[5][2] = "z"
//		arr[5][3] = "q2"
//		arr[5][4] = "z"
//
//		arr[6][0] = "q2"
//		arr[6][1] = "e"
//		arr[6][2] = "z"
//		arr[6][3] = "qf"
//		arr[6][4] = "e"
		index++
	}
}