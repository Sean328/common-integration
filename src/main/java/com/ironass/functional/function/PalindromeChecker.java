package com.ironass.functional.function;

/**
 * 回文检查
 *
 * @author lixin
 * @date 2019-02-15 16:49
 **/
@FunctionalInterface
public interface PalindromeChecker {
    boolean isPalindrome(String s);
}
