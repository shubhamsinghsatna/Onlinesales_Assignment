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
    var myApp : MyApp

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
                Text("$expression -> $result")

                qnaStatement = "${index + 1}. $expression -> $result"

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

        }
    }


}

//@Composable
//fun ExpressionInput(onEvaluate: (List<String>) -> Unit) {
//    var newExpression by remember { mutableStateOf(TextFieldValue()) }
//    var expressions by remember { mutableStateOf(emptyList<String>()) }
//
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        expressions.forEachIndexed { index, expression ->
//            BasicTextField(
//                value = expression,
//                onValueChange = { newValue ->
//                    expressions = expressions.toMutableList().apply {
//                        set(index, newValue)
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            )
//        }
//
//        BasicTextField(
//            value = newExpression.text,
//            onValueChange = { newValue ->
//                newExpression = TextFieldValue(newValue)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp)
//        )
//
//        Button(
//            onClick = {
//                // Add the new expression to the list
//                expressions = expressions + newExpression.text
//                // Clear the input field
//                newExpression = TextFieldValue("")
//
//                // Call the onEvaluate callback with the updated expressions
//                onEvaluate(expressions)
//            },
//            modifier = Modifier.padding(vertical = 8.dp)
//        ) {
//            Text("Evaluate Expressions")
//        }
//    }
//}
//
//@Composable
//fun ResultsList(results: List<String>) {
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Text(text = results.toString())
////        LazyColumn {
////            items(results) { result ->
////                ResultItem(result = result)
////            }
////        }
//    }
//}
//
//@Composable
//fun ResultItem(result: String) {
//    Text(
//        text = result,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        style = TextStyle(fontSize = 16.sp)
//    )
//}

//    var expression by remember { mutableStateOf("") }
//    var result by remember { mutableStateOf("") }
//    val mContext = LocalContext.current
//    val expr: List<String> = listOf("2+3","7+8","2/1")
//    val ans: List<String> = listOf("")
//    val precision: Int = 10
//    val expressionRequest = ExpressionRequest(expr = expr, precision = precision)
//    var expressionResponse = ExpressionResponse(result = ans, error = "")

//    Column {
//
//        Text(
//            text = "Input your expressions here",
//            style = MaterialTheme.typography.h6,
//            modifier = Modifier.padding(8.dp)
//        )
//        OutlinedTextField(
//            value = expression,
//            onValueChange = {
//                expression = it
//                result = "" // Clear the result when the expression changes
//            },
//            modifier = Modifier
//                .padding(8.dp)
//                .height(100.dp)
//                .fillMaxWidth(),
//            label = { Text("Input your expression") },
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//
//            onClick = {
//                expressionResponse = mathViewModel.evaluateExpressions(expressionRequest)
//                Log.e("expressionResponse calc screen",expressionResponse.result.toString())
//            },
//            modifier = Modifier.padding(all = Dp(10f)),
//            enabled = true,
//            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
//            shape = MaterialTheme.shapes.medium
//
//        ) {
//            Text(text = "Get Result")
//        }
//
//        Text(
//            text = "Your question is: $expressionRequest.expr.toString()",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Start
//        )
//
//        if(expressionResponse.result!=null){
//            Text(
//                text = "Your Answer is: ${expressionResponse.result}",
//                modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Start
//            )
//        }
////        Text(
////            text = "Your Answer is: $expressionResponse.expr.toString()",
////            modifier = Modifier.fillMaxWidth(),
////            textAlign = TextAlign.Start
////        )
//
//
//        Log.e("mathViewModel calcScreen",mathViewModel.errorMessage.value.toString())
//        if (mathViewModel.errorMessage.value != null) {
//            Text(
//                text = "Please input expressions only",
//                color = Color.Red,
//                modifier = Modifier.padding(10.dp)
//            )
//        }
//
//        // Output text
//        Text(
//            text = result,
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Start
//        )
//
//
//    }

