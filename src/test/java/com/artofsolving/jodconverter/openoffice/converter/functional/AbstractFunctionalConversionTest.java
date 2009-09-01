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

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.converter.AbstractConverterTest;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;


public abstract class AbstractFunctionalConversionTest extends AbstractConverterTest {

	private DocumentConverter documentConverter;

	protected void setUp() throws Exception {
		super.setUp();
		
		// use the default one for functional tests 
		documentConverter = new OpenOfficeDocumentConverter(getOpenOfficeConnection());
	}

	protected DocumentConverter getDocumentConverter() {
		return documentConverter;
	}
}
