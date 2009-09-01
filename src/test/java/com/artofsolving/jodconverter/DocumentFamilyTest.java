//
// JODConverter - Java OpenDocument Converter
// Copyright (C) 2004-2007 - Mirko Nasato <mirko@artofsolving.com>
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
// http://www.gnu.org/copyleft/lesser.html
//
package com.artofsolving.jodconverter;

import com.artofsolving.jodconverter.DocumentFamily;

import junit.framework.TestCase;

public class DocumentFamilyTest extends TestCase {

	public void testValidFamilies() {
		assertEquals(DocumentFamily.TEXT, DocumentFamily.getFamily("Text"));
		assertEquals(DocumentFamily.SPREADSHEET, DocumentFamily.getFamily("Spreadsheet"));
		assertEquals(DocumentFamily.PRESENTATION, DocumentFamily.getFamily("Presentation"));
	}

	public void testInvalidFamily() {
		try {
			DocumentFamily.getFamily("Invalid");
			fail("invalid family should throw an exception");
		} catch (IllegalArgumentException illegalArgumentException) {
			// expected
		}
	}
}
