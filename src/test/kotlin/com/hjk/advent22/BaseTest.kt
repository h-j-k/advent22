package com.hjk.advent22

import java.util.Locale

abstract class BaseTest {
    val input = javaClass.simpleName.replace("Test", ".txt").lowercase(Locale.getDefault())
        .let(ClassLoader::getSystemResource).readText().split(System.lineSeparator())
}