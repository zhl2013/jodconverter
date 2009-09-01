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
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;

import junit.framework.TestCase;

public class SocketOpenOfficeConnectionTest extends TestCase {

	public void testInvalidConnection() throws ConnectException {
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(59999);
		try {
			connection.connect();
			fail("should throw exception");
		} catch (ConnectException connectException) {
			// expected
		}
	}

	public void testConnectAndDisconnect() throws IOException {
		OpenOfficeConnection connection = new SocketOpenOfficeConnection();
		connection.connect();
		assertTrue(connection.isConnected());
		assertNotNull(connection.getDesktop());
		assertNotNull(connection.getBridge());
		assertNotNull(connection.getComponentContext());
		assertNotNull(connection.getRemoteServiceManager());
		connection.disconnect();
	}

	public void testAutoConnect() throws IOException {
		OpenOfficeConnection connection = new SocketOpenOfficeConnection();
		assertFalse(connection.isConnected());
		assertNotNull(connection.getDesktop());
		assertTrue(connection.isConnected());
		connection.disconnect();
	}

	public void testAutoReconnectAfterUnexpectedDisconnection() throws IOException {
		SocketOpenOfficeConnection connection = new SocketOpenOfficeConnection();
		connection.connect();
		assertTrue(connection.isConnected());
		assertNotNull(connection.getDesktop());
		
		connection.simulateUnexpectedDisconnection();
		assertFalse(connection.isConnected());
		
		// getDesktop() should force the reconnect
		assertNotNull(connection.getDesktop());
		assertTrue(connection.isConnected());
		
		connection.disconnect();
	}

	public void testMultipleConnections() throws Exception {
		OpenOfficeConnection connection1 = new SocketOpenOfficeConnection();
		OpenOfficeConnection connection2 = new SocketOpenOfficeConnection();
		
		connection1.connect();
		assertTrue(connection1.isConnected());
		connection2.connect();
		assertTrue(connection2.isConnected());
		
		connection1.disconnect();
		connection2.disconnect();
	}
}
