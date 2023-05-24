package org.wso2.identity.integration.test.rest.api.user.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class PatchRoleOperationRequestObject {

    private List<String> schemas = null;
    private List<RoleItemAddGroupobj> Operations = null;

    /**
     *
     **/
    public PatchRoleOperationRequestObject schemas(List<String> schemas) {

        this.schemas = schemas;
        return this;
    }

    @ApiModelProperty()
    @JsonProperty("schemas")
    @Valid
    public List<String> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<String> schemas) {
        this.schemas = schemas;
    }

    /**
     *
     **/
    public PatchRoleOperationRequestObject Operations(List<RoleItemAddGroupobj> Operations) {

        this.Operations = Operations;
        return this;
    }

    @ApiModelProperty()
    @JsonProperty("Operations")
    @Valid
    public List<RoleItemAddGroupobj> getOperations() {
        return Operations;
    }

    public void setOperations(List<RoleItemAddGroupobj> Operations) {
        this.Operations = Operations;
    }

    public PatchRoleOperationRequestObject addOperations(RoleItemAddGroupobj Operation) {
        if (this.Operations == null) {
            this.Operations = new ArrayList<>();
        }
        this.Operations.add(Operation);
        return this;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatchRoleOperationRequestObject user = (PatchRoleOperationRequestObject) o;
        return Objects.equals(this.schemas, user.schemas) &&
                Objects.equals(this.Operations, user.Operations);

    }

    @Override
    public int hashCode() {
        return Objects.hash(schemas, Operations);
    }

    @Override
    public String toString() {

        return "class PatchRoleOperationRequestObject {\n" +
                "    schemas: " + toIndentedString(schemas) + "\n" +
                "    Operations: " + toIndentedString(Operations) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {

        if (o == null) {
            return "null";
        }
        return o.toString();
    }
}