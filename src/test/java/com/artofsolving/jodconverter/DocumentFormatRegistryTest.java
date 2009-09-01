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

import junit.framework.TestCase;

public class DocumentFormatRegistryTest extends TestCase {

	private DocumentFormatRegistry defaultRegistry = new DefaultDocumentFormatRegistry();

	public void testDefaults() {
		DocumentFormat pdf1 = defaultRegistry.getFormatByFileExtension("pdf");
		assertNotNull(pdf1);
		DocumentFormat pdf2 = defaultRegistry.getFormatByMimeType("application/pdf");
		assertNotNull(pdf2);
		assertEquals(pdf1, pdf2);
	}

	public void testUnknownFormats() {
		DocumentFormat unknown1 = defaultRegistry.getFormatByFileExtension("xyz");
		assertNull(unknown1);
		DocumentFormat unknown2 = defaultRegistry.getFormatByMimeType("type/xyz");
		assertNull(unknown2);
	}

    public void testCaseInsensitivity() {
        DocumentFormat pdf = defaultRegistry.getFormatByFileExtension("PDF");
        assertNotNull(pdf);
        assertEquals(defaultRegistry.getFormatByFileExtension("pdf"), pdf);
    }
}
