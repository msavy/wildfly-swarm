/**
 * Copyright 2018 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wildfly.swarm.microprofile.openapi.runtime;

import java.io.File;

import org.eclipse.microprofile.config.Config;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.json.JSONException;
import org.junit.Ignore;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.wildfly.microprofile.config.WildFlyConfigBuilder;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.microprofile.openapi.io.OpenApiSerializer;
import org.wildfly.swarm.microprofile.openapi.io.OpenApiSerializer.Format;

/**
 * Runs the {@link OpenApiDeploymentProcessor} against the Apiman Manager API war.
 * @author eric.wittmann@gmail.com
 */
@SuppressWarnings("rawtypes")
@Ignore
public class ApimanManagerApiDeploymentTest {
//
//    /**
//     * Loads a resource as a string (reads the content at the URL).
//     * @param testResource
//     * @throws IOException
//     */
//    private static String loadResource(URL testResource) throws IOException {
//        return IOUtils.toString(testResource, "UTF-8");
//    }

    /**
     * Compares two JSON strings.
     * @param expected
     * @param actual
     * @throws JSONException
     */
    private static void assertJsonEquals(String expected, String actual) throws JSONException {
        JSONAssert.assertEquals(expected, actual, true);
    }

    /**
     * Common test method.
     * @throws Exception
     */
    @Test
    public void testApimanWAR() throws Exception {
        File warFile = Maven.resolver().resolve("io.apiman:apiman-manager-api-war:war:1.3.1.Final").withoutTransitivity().asSingleFile();

        WildFlyConfigBuilder cfgBuilder = new WildFlyConfigBuilder();
        cfgBuilder.addDefaultSources();
        Config cfg = cfgBuilder.build();
        Archive archive = ShrinkWrap.createFromZipFile(JAXRSArchive.class, warFile);
        OpenApiConfig config = new OpenApiConfig() {
            @Override
            protected Config getConfig() {
                return cfg;
            }
        };

        OpenApiDeploymentProcessor processor = new OpenApiDeploymentProcessor(config, archive);
        processor.process();

        String actual = OpenApiSerializer.serialize(OpenApiDocumentHolder.document, Format.JSON);
//        String expected = loadResource(getClass().getResource(expectedResource));
        String expected = "{}";

        assertJsonEquals(expected, actual);
    }

}
