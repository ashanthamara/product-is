/*
 * Copyright (c) 2019, WSO2 LLC. (http://www.wso2.com).
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

package org.wso2.identity.integration.test.rest.api.server.application.management.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import javax.validation.Valid;

public class ClaimMappings  {
  
    private String applicationClaim;
    private Claim localClaim;

    /**
    **/
    public ClaimMappings applicationClaim(String applicationClaim) {

        this.applicationClaim = applicationClaim;
        return this;
    }
    
    @ApiModelProperty(example = "firstname")
    @JsonProperty("applicationClaim")
    @Valid
    public String getApplicationClaim() {
        return applicationClaim;
    }
    public void setApplicationClaim(String applicationClaim) {
        this.applicationClaim = applicationClaim;
    }

    /**
    **/
    public ClaimMappings localClaim(Claim localClaim) {

        this.localClaim = localClaim;
        return this;
    }
    
    @ApiModelProperty(example = "http://wso2.org/claims/givenname")
    @JsonProperty("localClaim")
    @Valid
    public Claim getLocalClaim() {
        return localClaim;
    }
    public void setLocalClaim(Claim localClaim) {
        this.localClaim = localClaim;
    }



    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClaimMappings claimMappings = (ClaimMappings) o;
        return Objects.equals(this.applicationClaim, claimMappings.applicationClaim) &&
            Objects.equals(this.localClaim, claimMappings.localClaim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationClaim, localClaim);
    }

    @Override
    public String toString() {

        return "class ClaimMappings {\n" +
                "    applicationClaim: " + toIndentedString(applicationClaim) + "\n" +
                "    localClaim: " + toIndentedString(localClaim) + "\n" +
                "}";
    }

    /**
    * Convert the given object to string with each line indented by 4 spaces
    * (except the first line).
    */
    private String toIndentedString(Object o) {

        if (o == null) {
            return "null";
        }
        return o.toString();
    }
}

