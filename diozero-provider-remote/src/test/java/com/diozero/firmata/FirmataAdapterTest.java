package com.diozero.firmata;

/*-
 * #%L
 * Organisation: mattjlewis
 * Project:      Device I/O Zero - Remote Provider
 * Filename:     FirmataAdapterTest.java  
 * 
 * This file is part of the diozero project. More information about this project
 * can be found at http://www.diozero.com/
 * %%
 * Copyright (C) 2016 - 2020 mattjlewis
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("static-method")
public class FirmataAdapterTest {
	@Test
	public void testConversion() {
		byte[] bytes = { 0x7f, 0x01 };
		int value = 255;
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes[0], bytes[1]));
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToLsbMsb(value));
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		
		bytes = new byte[] { 0x7f, 0x07 };
		value = 1023;
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes[0], bytes[1]));
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToLsbMsb(value));
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));

		value = 127;
		bytes = new byte[] { 0x7f, 0x00 };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToLsbMsb(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes[0], bytes[1]));
		bytes = new byte[] { 0x7f };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes[0]));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));
		
		bytes = new byte[] { 0x7f };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));

		value = 128;
		bytes = new byte[] { 0x0, 0x01 };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToLsbMsb(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes[0], bytes[1]));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));

		value = 129;
		bytes = new byte[] { 0x1, 0x01 };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToLsbMsb(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes[0], bytes[1]));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));

		value = 16383;
		bytes = new byte[] { 0x7f, 0x7f };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToLsbMsb(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes[0], bytes[1]));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));

		value = 16384;
		bytes = new byte[] { 0x00, 0x00, 0x01 };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));

		value = 16385;
		bytes = new byte[] { 0x01, 0x00, 0x01 };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));
		
		value = 2097151;
		bytes = new byte[] { 0x7f, 0x7f, 0x7f };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));
		
		value = 2097152;
		bytes = new byte[] { 0x00, 0x00, 0x00, 0x01 };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));
		
		value = 2097153;
		bytes = new byte[] { 0x01, 0x00, 0x00, 0x01 };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		Assert.assertEquals(value, FirmataAdapter.convertToValue(bytes));
		
		value = 268435456;
		bytes = new byte[] { 0x00 };
		Assert.assertArrayEquals(bytes, FirmataAdapter.convertToBytes(value));
		
		Assert.assertEquals(0, 7 >> 3);
		Assert.assertEquals(1, 8 >> 3);
		Assert.assertEquals(1, 15 >> 3);
		Assert.assertEquals(2, 16 >> 3);
		Assert.assertEquals(2, 23 >> 3);
		Assert.assertEquals(3, 24 >> 3);
		Assert.assertEquals(3, 31 >> 3);
		Assert.assertEquals(4, 32 >> 3);
	}
}
