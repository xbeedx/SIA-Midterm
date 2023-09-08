/**
 * Copyright 2005-2020 Talend
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or or EPL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * https://restlet.talend.com/
 * 
 * Restlet is a registered trademark of Talend S.A.
 */

package org.restlet.test.ext.jaxrs.services.tests;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.test.ext.jaxrs.services.resources.RecursiveTestService;

/**
 * @author Stephan Koops
 * @see RecursiveTestService
 */
public class RecursiveTest extends JaxRsTestCase {
    @Override
    protected Application getApplication() {
        final Application appConfig = new Application() {
            @Override
            @SuppressWarnings({ "unchecked", "rawtypes" })
            public Set<Class<?>> getClasses() {
                return (Set) Collections.singleton(RecursiveTestService.class);
            }
        };
        return appConfig;
    }

    public void test0() throws Exception {
        final Response response = get();
        sysOutEntityIfError(response);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        assertEquals("0", response.getEntity().getText());
    }

    public void test1a() throws Exception {
        final Response response = get("a");
        sysOutEntityIfError(response);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        assertEquals("1", response.getEntity().getText());
    }

    public void test1b() throws Exception {
        final Response response = get("a/");
        sysOutEntityIfError(response);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        assertEquals("1", response.getEntity().getText());
    }

    public void test2() throws Exception {
        final Response response = get("a/a");
        sysOutEntityIfError(response);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        assertEquals("2", response.getEntity().getText());
    }

    public void test2b() throws Exception {
        final Response response = get("a/a/");
        sysOutEntityIfError(response);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        assertEquals("2", response.getEntity().getText());
    }

    public void test3() throws Exception {
        final Response response = get("a/a/a");
        sysOutEntityIfError(response);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        assertEquals("3", response.getEntity().getText());
    }
}
