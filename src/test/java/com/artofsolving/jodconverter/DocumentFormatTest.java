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

import java.util.Map;

import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;

import junit.framework.TestCase;

public class DocumentFormatTest extends TestCase {

	public void testNewInstanceForDeserialization() throws InstantiationException, IllegalAccessException {
		// XStream uses reflection on some JVMs to deserialize from XML
		DocumentFormat documentFormat = (DocumentFormat) DocumentFormat.class.newInstance();
		assertNotNull(documentFormat);
	}

	public void testBasicFields() {
		DocumentFormat documentFormat = new DocumentFormat("A Text Format", DocumentFamily.TEXT, "type/a-text", "atf");
		assertEquals("A Text Format", documentFormat.getName());
		assertEquals(DocumentFamily.TEXT, documentFormat.getFamily());
		assertEquals("type/a-text", documentFormat.getMimeType());
		assertEquals("atf", documentFormat.getFileExtension());		
	}

	public void testExportOptions() {
		DocumentFormat documentFormat = new DocumentFormat("A Text Format", DocumentFamily.TEXT, "type/a-text", "atf");
		
		Map defaultExportOptions = documentFormat.getExportOptions(DocumentFamily.TEXT);
		assertNotNull(defaultExportOptions);
		assertTrue(defaultExportOptions.isEmpty());
		
		documentFormat.setExportOption(DocumentFamily.TEXT, "option1", "a");
		documentFormat.setExportOption(DocumentFamily.TEXT, "option2", "b");
		Map exportOptions = documentFormat.getExportOptions(DocumentFamily.TEXT);
		assertEquals(2, exportOptions.size());
		assertEquals("a", exportOptions.get("option1"));
		assertEquals("b", exportOptions.get("option2"));		
	}

    public void testImportOptions() {
        DocumentFormat documentFormat = new DocumentFormat("A Text Format", DocumentFamily.TEXT, "type/a-text", "atf");
        
        Map defaultImportOptions = documentFormat.getImportOptions();
        assertNotNull(defaultImportOptions);
        assertTrue(defaultImportOptions.isEmpty());
        
        documentFormat.setImportOption("option1", "a");
        documentFormat.setImportOption("option2", "b");
        Map importOptions = documentFormat.getImportOptions();
        assertEquals(2, importOptions.size());
        assertEquals("a", importOptions.get("option1"));
        assertEquals("b", importOptions.get("option2"));
    }

	public void testImportableFlag() {
		DocumentFormat exportOnlyFormat = new DocumentFormat("Export Only Format", "type/export-only", "eof");
		assertTrue("should be export-only", exportOnlyFormat.isExportOnly());
		assertFalse("should not be importable", exportOnlyFormat.isImportable());		
		
		DocumentFormat importFormat = new DocumentFormat("Import Text Format", DocumentFamily.TEXT, "type/text-import", "itf");
		assertTrue("should be importable", importFormat.isImportable());
		assertFalse("should not be export-only", importFormat.isExportOnly());
	}

	public void testExportableFromAndTo() {
		DocumentFormat exportFormat1 = new DocumentFormat("Export Format One", "type/export-1", "ef1");
		exportFormat1.setExportFilter(DocumentFamily.TEXT, "export-1-filter");
		exportFormat1.setExportFilter(DocumentFamily.SPREADSHEET, "export-1-filter");
		assertTrue(exportFormat1.isExportableFrom(DocumentFamily.TEXT));
		assertTrue(exportFormat1.isExportableFrom(DocumentFamily.SPREADSHEET));
		assertFalse(exportFormat1.isExportableFrom(DocumentFamily.PRESENTATION));
		
		DocumentFormat exportFormat2 = new DocumentFormat("Export Format Two", "type/export-2", "ef2");
		exportFormat2.setExportFilter(DocumentFamily.TEXT, "export-2-filter");
		assertTrue(exportFormat2.isExportableFrom(DocumentFamily.TEXT));
		assertFalse(exportFormat2.isExportableFrom(DocumentFamily.SPREADSHEET));
		assertFalse(exportFormat2.isExportableFrom(DocumentFamily.PRESENTATION));
		
		DocumentFormat textFormat = new DocumentFormat("A Text Format", DocumentFamily.TEXT, "type/a-text", "atf");		
		assertTrue(textFormat.isExportableTo(exportFormat1));
		assertTrue(textFormat.isExportableTo(exportFormat2));
		
		DocumentFormat spreadsheetFormat = new DocumentFormat("A Spreadsheet Format", DocumentFamily.SPREADSHEET, "type/a-spreadsheet", "asf");
		assertTrue(spreadsheetFormat.isExportableTo(exportFormat1));
		assertFalse(spreadsheetFormat.isExportableTo(exportFormat2));
		
		DocumentFormat presentationFormat = new DocumentFormat("A Presentation Format", DocumentFamily.PRESENTATION, "type/a-presentation", "apf");
		assertFalse(presentationFormat.isExportableTo(exportFormat1));
		assertFalse(presentationFormat.isExportableTo(exportFormat2));
	}
}
