package com.onlinesales.onlinesalescalcapp.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.onlinesales.onlinesalescalcapp.RoomDatabase.MyApp
import com.onlinesales.onlinesalescalcapp.RoomDatabase.QnaData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HistoryScreen(context: Context = LocalContext.current) {

    val database = (context.applicationContext as MyApp).database
    val dao = database.qnaDao()

    // Fetch historical data from the database
    val historicalData: List<QnaData> by dao.getAllQna().observeAsState(emptyList())

    // Display historical data using DisplayHistory composable
    DisplayHistory(qnaList = historicalData)

//    val database = (context.applicationContext as MyApp).database
//    val dao = database.qnaDao()
//
//    var historicalData: List<QnaData> = emptyList()
//
//    CoroutineScope(Dispatchers.IO).launch {
//        historicalData = dao.getAllQna()
//        Log.e("historicalData in", historicalData.toString())
//        DisplayHistory(qnaList = historicalData)
//    }
//    Log.e("historicalData out", historicalData.toString())
//    // Display historical data using DisplayHistory composable
//    DisplayHistory(qnaList = historicalData)


}

@Composable
fun DisplayHistory(qnaList: List<QnaData>) {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        qnaList.map { item ->
            expressioView(expression = item.expression )
        }
    }


}

@Composable
fun expressioView(expression: String){
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = "Expressions",
                    style = MaterialTheme.typography.h6
                )
                Text(text = expression,
                    style = MaterialTheme.typography.subtitle1)
            }
        }
    }
}


//@Composable
//fun QnaList(qnaList: List<QnaData>) {
//    // LazyColumn to display the list of QnaData items
//    LazyColumn {
//        items(qnaList) { qnaItem ->
//            QnaItem(qnaItem)
//        }
//    }
//}
//
//@Composable
//fun QnaItem(qnaData: QnaData) {
//    // Composable function to display a single QnaData item
//    // Customize this as per your UI requirements
//    Text("${qnaData.id}: ${qnaData.expression}")
//}