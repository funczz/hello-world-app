package com.github.funczz.hello_world.app

import com.github.funczz.hello_world.lib.HelloWorld
import com.github.funczz.hello_world.lib.IGreeting

/**
 * hello-world アプリ
 * @author funczz
 */
class MainClass(
        private val greeting: IGreeting = HelloWorld()
) : IGreeting by greeting {

    /**
     * 標準出力へ文字列を出力する
     */
    fun invoke() {
        println(get())
    }

    companion object {

        /**
         * メインクラス
         */
        @JvmStatic
        fun main(args: Array<String>) {
            MainClass().invoke()
        }

    }

}