package ru.invitro.adapter.model.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MetadataModel {
    @NonNull
    private String contactId;
    @NonNull
    private String officeId;
    @NonNull
    private DocumentType documentType;
    @NonNull
    private UUID documentId;
}
