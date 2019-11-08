package com.wuyubin.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.wuyubin.StringUtils;

public class TestStringUtils {
	
	@Test
	public void testIsBlank() {
		
		
		assertTrue( StringUtils.isBlank("   "));
		
	}
	
	
	@Test
	public void testIsBlank2() {
		
		assertFalse( StringUtils.isBlank("aaaa"));
	}
	
	@Test
	public void testRandomn() {
		String s = StringUtils.getRandomStr(10);
		System.out.println(" s is :: " + s);
	}
	
	@Test
	public void testStrRland() {
		String s = StringUtils.getRandomStr2(25);
		System.out.println(" s is " + s + "  and ������  " + s.length());
	}
	
	
	@Test
	public void testCnStrn() {
		String s = StringUtils.getRandomCnn(25);
		System.out.println(" s " + s + "  changdu : " + s.length());
		
	}
	
	
	
	
	
}
