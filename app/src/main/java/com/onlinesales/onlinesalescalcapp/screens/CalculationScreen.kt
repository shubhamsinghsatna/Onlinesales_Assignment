package com.onlinesales.onlinesalescalcapp.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.onlinesales.onlinesalescalcapp.RoomDatabase.MyApp
import com.onlinesales.onlinesalescalcapp.RoomDatabase.QnaData
import com.onlinesales.onlinesalescalcapp.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun CalculationScreen(viewModel: MathViewModel, context: Context = LocalContext.current) {

    var inputText by remember { mutableStateOf("") }
    var inputTextShow by remember { mutableStateOf("") }
    var qnaStatement : String = ""
    var listOfExpression: List<String> = emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter mathematical expressions") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                inputTextShow = ""
                viewModel.evaluateExpressions(inputText)
                inputTextShow = inputText
                inputText = ""
                insertDataInDB()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Evaluate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Display results here
        Text("Results:")
        Column {
            //To show error
//            Text(text = viewModel.error)
            viewModel.results.value.forEachIndexed { index, result ->
                val expression = inputTextShow.lines()[index]
                qnaStatement = "${index + 1}. $expression -> $result"
                listOfExpression = listOfExpression + qnaStatement
                var a = qnaStatement

                Text(a)
                Log.e("Data expression",listOfExpression.get(index))

                val qnaData = QnaData(
                    id = 0,
                    expression = qnaStatement,
                    submissionDate = System.currentTimeMillis()
                )

                val database = (context.applicationContext as MyApp).database
                CoroutineScope(Dispatchers.IO).launch {
                    database.qnaDao().insertQna(qnaData)
                    Log.e("Data inserted",qnaData.toString())
                }
            }
//            viewModel.results.value = emptyList()
        }
    }
}

fun insertDataInDB(){

}

