package com.artofsolving.jodconverter.openoffice.connection;

import com.artofsolving.jodconverter.test.OpenOfficeTestContext;

import junit.framework.TestCase;

public class OpenOfficeConfigurationTest extends TestCase {

    private OpenOfficeConfiguration configuration;

    protected void setUp() throws Exception {
        super.setUp();
        OpenOfficeConnection connection = OpenOfficeTestContext.getInstance().getOpenOfficeConnection();
        configuration = new OpenOfficeConfiguration(connection);
    }
    
    public void testVersion() {
        checkNotEmpty(configuration.getOpenOfficeVersion());
    }

    public void testLocale() {
        checkNotEmpty(configuration.getOpenOfficeLocale());
    }

    private void checkNotEmpty(String value) {
        assertNotNull(value);
        assertTrue(value.length() > 0);
    }
}
