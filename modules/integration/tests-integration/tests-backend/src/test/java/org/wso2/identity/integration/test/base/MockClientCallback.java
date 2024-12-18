/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.identity.integration.test.base;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.ResponseTransformerV2;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.http.Response;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import org.wso2.identity.integration.common.utils.ISIntegrationTest;
import org.wso2.identity.integration.test.util.Utils;

import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

/**
 * Mock client callback endpoint to test OIDC related flows.
 */
public class MockClientCallback {

    public static final String CALLBACK_URL = "https://localhost:8091/dummyApp/oauth2client";

    private final AtomicReference<String> authorizationCode = new AtomicReference<>();

    private WireMockServer wireMockServer;

    public void start() {

        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig()
                .httpsPort(8091)
                .httpDisabled(true)
                .keystorePath(Paths.get(Utils.getResidentCarbonHome(), "repository", "resources", "security",
                        ISIntegrationTest.KEYSTORE_NAME).toAbsolutePath().toString())
                .keystorePassword("wso2carbon")
                .keyManagerPassword("wso2carbon")
                .extensions(new ResponseTemplateTransformer(null, true, null, null),
                        new ResponseTransformerV2() {

                            @Override
                            public Response transform(Response response, ServeEvent serveEvent) {

                                authorizationCode.set(serveEvent.getRequest().getQueryParams().get("code").firstValue());
                                return response;
                            }

                            @Override
                            public boolean applyGlobally() {
                                return false;
                            }

                            @Override
                            public String getName() {
                                return "authz-code-transformer";
                            }
                        }));

        wireMockServer.start();

        // Configure the mock client endpoints.
        configureMockEndpoints();
    }

    public void stop() {

        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    private void configureMockEndpoints() {

        try {
            wireMockServer.stubFor(get(urlPathEqualTo("/dummyApp/oauth2client"))
                    .withQueryParam("code", matching(".*"))
                    .willReturn(aResponse()
                            .withTransformers("response-template", "authz-code-transformer")
                            .withStatus(200)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getAuthorizationCode() {

        return authorizationCode.get();
    }
}
