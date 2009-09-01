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
package com.artofsolving.jodconverter.openoffice.converter.functional;

import java.io.File;
import java.io.IOException;

import com.artofsolving.jodconverter.test.FileType;


public class TextConversionTest extends AbstractFunctionalConversionTest {

	public void testOdtToTxt() throws IOException {
		File outputFile = convertAndCheck("hello.odt", "txt", FileType.TXT);
		assertEquals("output content", "Hello from an OpenDocument Text!", readTextContent(outputFile));
	}

	public void testOdtToDoc() throws IOException {
		convertAndCheck("hello.odt", "doc", FileType.MSOFFICE);
	}

	public void testOdtToRtf() throws IOException {
		convertAndCheck("hello.odt", "rtf", FileType.RTF);
	}

	public void testOdtToSxw() throws IOException {
		convertAndCheck("hello.odt", "sxw", FileType.SXW);
	}

	public void testOdtToHtml() throws IOException {
		convertAndCheck("hello.odt", "html", FileType.HTML);
	}

	// fails with OOo 2.3.1, passes with 2.0.4: XHTML must have been removed at some point
	public void _testOdtToXhtml() throws IOException {
		convertAndCheck("hello.odt", "xhtml", FileType.XHTML);
	}

	// requires OOo >= 2.3.0
    public void testOdtToWikitext() throws IOException {
        convertAndCheck("hello.odt", "wiki", FileType.TXT);
    }
	
	public void testDocToOdt() throws IOException {
		convertAndCheck("hello.doc", "odt", FileType.ODT);
	}

	public void testSxwToOdt() throws IOException {
		convertAndCheck("hello.sxw", "odt", FileType.ODT);
	}

	public void testRtfToOdt() throws IOException {
		convertAndCheck("hello.rtf", "odt", FileType.ODT);
	}

	public void testHtmlToOdt() throws IOException {
		convertAndCheck("hello.html", "odt", FileType.ODT);
	}

    public void testTxtToOdt() throws IOException {
        convertAndCheck("hello-dos.txt", "odt", FileType.ODT);
        convertAndCheck("hello-unix.txt", "odt", FileType.ODT);
    }
}
