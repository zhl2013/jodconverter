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
package com.artofsolving.jodconverter.openoffice.converter;

import java.io.IOException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.artofsolving.jodconverter.test.FileType;


public class StreamOpenOfficeDocumentConverterTest extends AbstractConverterTest {
	
	private DocumentConverter converter;

	protected void setUp() throws Exception {
		super.setUp();
		converter = new StreamOpenOfficeDocumentConverter(getOpenOfficeConnection(), getDocumentFormatRegistry());
	}

	protected DocumentConverter getDocumentConverter() {
		return converter;
	}

	public void testValidConversion() throws IOException {
		convertAndCheck("hello.odt", "pdf", FileType.PDF);
	}

	public void testCorruptedInputFile() throws IOException {
		try {
			convertAndCheck("invalid.odt", "pdf", FileType.PDF);
			fail("should have detected that the input file was invalid");
		} catch (OpenOfficeException openOfficeException) {
			assertTrue(openOfficeException.getMessage().startsWith("conversion failed"));
		}
	}
}
