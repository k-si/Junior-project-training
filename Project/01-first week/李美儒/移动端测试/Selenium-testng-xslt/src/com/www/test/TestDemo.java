package com.www.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestDemo {

	@Test
	public void test1() {
		assertEquals(1, 1);
	}
	@Test
	public void test2() {
		assertEquals(1, 2);
	}
	@Test
	public void test3() {
		assertEquals(1, 3);
	}
	@Test
	public void test4() {
		assertEquals(2, 2);
	}
	@Test
	public void test5() {
		assertEquals(1, 5);
	}
}
