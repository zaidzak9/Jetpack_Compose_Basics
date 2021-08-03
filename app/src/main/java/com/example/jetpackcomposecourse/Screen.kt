package com.example.jetpackcomposecourse

sealed class Screen(val route:String){
    object MainScreen : Screen("main_screen")
    object GreetingScreen : Screen("greeting_screen")

    fun withArgs(vararg args : String):String{
        return buildString {
            append(route)
            args.forEach {args->
                append("/$args")
            }
        }
    }
}
