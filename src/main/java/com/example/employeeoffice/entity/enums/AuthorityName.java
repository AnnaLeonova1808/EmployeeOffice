package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different authority names for actions on documents.
 */
@Schema(description = "Names of authorities for document actions")
public enum AuthorityName {
    /**
     * Authority to read a document.
     */
    @Schema(description = "Authority to read a document")
    READ_DOCUMENT,

    /**
     * Authority to create a document.
     */
    @Schema(description = "Authority to create a document")
    CREATE_DOCUMENT,

    /**
     * Authority to delete a document.
     */
    @Schema(description = "Authority to delete a document")
    DELETE_DOCUMENT,

    /**
     * Authority to update a document.
     */
    @Schema(description = "Authority to update a document")
    UPDATE_DOCUMENT;
}
