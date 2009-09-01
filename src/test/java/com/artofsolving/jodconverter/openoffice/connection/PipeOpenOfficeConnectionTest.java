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
package com.artofsolving.jodconverter.openoffice.connection;

import java.io.IOException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.PipeOpenOfficeConnection;

import junit.framework.TestCase;

public class PipeOpenOfficeConnectionTest extends TestCase {

	public void testInvalidConnection() throws ConnectException {
		OpenOfficeConnection connection = new PipeOpenOfficeConnection("no-such-pipe-name");
		try {
			connection.connect();
			fail("should throw exception");
		} catch (ConnectException connectException) {
			// expected
		}
	}

	public void testConnectAndDisconnect() throws IOException {
		OpenOfficeConnection connection = new PipeOpenOfficeConnection();
		connection.connect();
		assertTrue(connection.isConnected());
		assertNotNull(connection.getDesktop());
		connection.disconnect();
	}
}
