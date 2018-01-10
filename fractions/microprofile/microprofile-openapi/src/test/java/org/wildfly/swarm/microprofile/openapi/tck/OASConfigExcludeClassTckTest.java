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

package org.wildfly.swarm.microprofile.openapi.tck;

import org.eclipse.microprofile.openapi.tck.OASConfigExcludeClassTest;

import io.restassured.response.ValidatableResponse;
import test.org.wildfly.swarm.microprofile.openapi.BaseTckTest;
import test.org.wildfly.swarm.microprofile.openapi.TckTest;

/**
 * @author eric.wittmann@gmail.com
 */
@TckTest(test=OASConfigExcludeClassTest.class, configProperties="exclude-class-microprofile-config.properties")
public class OASConfigExcludeClassTckTest extends BaseTckTest {

    @Override
    public OASConfigExcludeClassTest getDelegate() {
        return new OASConfigExcludeClassTest() {
            @Override
            public ValidatableResponse callEndpoint(String format) {
                return doCallEndpoint(format);
            }
        };
    }

    @Override
    public Object[] getTestArguments() {
        return new String[] { "JSON" };
    }

}
