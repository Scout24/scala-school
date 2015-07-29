package com.autoscout24

import com.autoscout24.Koans._
import org.scalatest.FunSuite

class KoansTest extends FunSuite {

  test("returns the given string in reverse") {
    assert(reverse("abcd") equals "dcba")
    assert(reverse("Hello World") equals "dlroW olleH")
  }

  test("checks if a given string is a palindrome, ignoring case and punctuation") {
    assert(isPalindrome("Madam I'm Adam") equals true)
    assert(isPalindrome("Not a Palindrome") equals false)
  }

  test("returns the element at the nth position in a list") {
    assert(nth(List("a", "w", "4", "7"), 2) equals "4")
    assert(nth(List("Scala", "koans", "are", "fun"), 1) equals "koans")
  }

  test("returns the first n numbers in the Fibonacci sequence") {
    assert(fibonacci(3) equals List(1, 1, 2))
    assert(fibonacci(6) equals List(1, 1, 2, 3, 5, 8))
    assert(fibonacci(6) equals List(1, 1, 2, 3, 5, 8, 13, 21))
  }

  test("returns only the odd numbers in a sequence") {
    assert(onlyOdd(List(1,2,3,4,5,6)) equals List(1,3,5))
  }

  test("zips two lists together to form a map") {
    assert(zipMap(List("Hello", "World"), List("Hallo", "Welt")) equals Map("Hello" -> "Hallo", "World" -> "Welt"))
  }
}
