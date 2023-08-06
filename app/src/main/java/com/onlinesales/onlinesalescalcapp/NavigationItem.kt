package com.onlinesales.onlinesalescalcapp

sealed class NavigationItem(var route: String, var icon: Int, var title: String)
{
    object Calculation : NavigationItem("calculation", R.drawable.calculate_icon, "Calculation")
    object History : NavigationItem("history", R.drawable.work_history_icon, "History")
}
