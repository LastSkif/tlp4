package com.example.tyap4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

	var ruleE: EditText? = null
	var readE: EditText? = null
	var stekE: EditText? = null
	var nextE: EditText? = null
	var moveE: EditText? = null

	var text: TextView? = null
	//var but1: Button? = null

	var chasiki = 0
	var index = 0

	var arr: Array<Array<String>> = arrayOf()
	var alphabet: Array<String> = arrayOf()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val addB: Button = findViewById(R.id.Add)
		val nextB: Button = findViewById(R.id.NextButton)
		arr = Array(100, { Array(5, { "" }) })

		ruleE = findViewById(R.id.Rule)
		readE = findViewById(R.id.Read)
		stekE = findViewById(R.id.Stek)
		nextE = findViewById(R.id.NextRule)
		moveE = findViewById(R.id.Move)

		text = findViewById(R.id.text)

		addB.setOnClickListener {
			addRule()
			ruleE?.setText("")
			readE?.setText("")
			stekE?.setText("")
			nextE?.setText("")
			moveE?.setText("")
		}
		nextB.setOnClickListener {
			when (chasiki) {
				0 -> start()
			}
			//ruleE?.isVisible = false
			readE?.isVisible = false
			stekE?.isVisible = false
			nextE?.isVisible = false
			moveE?.isVisible = false
			addB?.isVisible = false
		}
	}

	fun start() {
		val stroka = ruleE?.text.toString() + "e"
		var stek = "z"
		var sost: String = "q0"
		var out: String = ""
		var out1: String = ""
		var bool = false
		var sym = false
		for (ch in stroka) {
			sym = false
			for (i in 0..99) {
				if (arr[i][1].isNotEmpty()) {
					sym = ch == arr[i][1][0]
					//out = out + arr[i][0] + " " + arr[i][1] + " " + arr[i][2] + " " + arr[i][3] + " " + arr[i][4] + '\n'
				}
				if (sym) break
			}
			if (!sym) break
		}

		if (stroka.isNotEmpty()) {
			for (ch in stroka) {
				out1=out
				for (i in 0..10) {
					if (arr[i][0].isNotEmpty())
						if (sost == arr[i][0] && ch.toString() == arr[i][1] && stek[0] == arr[i][2][0]
						) {
							sost = arr[i][3]
							if (arr[i][4].length == 1 && arr[i][4][0] == 'e') stek = stek.drop(1)
							else if (arr[i][4].length == 1) stek = stek
							else stek = arr[i][1] + stek
							out = out + stek + '\n'
							break
						}
				}
				if(out==out1) bool=true
			}
		}
		if (sost != "qf") bool = true
		when {
			!sym -> text?.text = "Присутсвуют лишние символы"
			bool -> text?.text = out + "Цепочка не выпонима"
			else -> text?.text = out + "Цепочка выполнима"
		}
	}

	fun addRule() {
		arr[index][0] = ruleE?.text.toString()
		arr[index][1] = readE?.text.toString()
		arr[index][2] = stekE?.text.toString()
		arr[index][3] = nextE?.text.toString()
		arr[index][4] = moveE?.text.toString()
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