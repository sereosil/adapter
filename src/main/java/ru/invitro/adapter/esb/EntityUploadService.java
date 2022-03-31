package ru.invitro.adapter.esb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.invitro.armps.common.service.impl.TransactionExecutor;
import ru.invitro.armps.integration.esb.publisher.util.XmlFormatter;
import ru.invitro.armps.integration.esb.remote.IPublicationService;
import ru.invitro.armps.integration.esb.remote.contact.Contacts;
import ru.invitro.armps.integration.esb.remote.operationResponse.Successful;

/**
 * Класс для отправки данных в шину.
 */
@Slf4j
@Service
public class EntityUploadService {

    private final IPublicationService outgoingPublicationService;

    private final TransactionExecutor transactionExecutor;


    public EntityUploadService(@Qualifier("integration-esb-outgoing-publication-service") final IPublicationService outgoingPublicationService) {
        this.outgoingPublicationService = outgoingPublicationService;
        this.transactionExecutor = new TransactionExecutor();
    }


    /**
     * Запустить механизм публикации сущности в шину.
     *
     * @param entity сущность
     * @param isOnlinePush флаг что надо отправить прямо сейчас
     * @return Объект с информацией о ходе публикации
     */
    public PublicationInfo handlePush(final Contacts entity, final OperationType type, final boolean isOnlinePush) {
        final Contacts contactsEntity = entity;
        final String request = XmlFormatter.format(contactsEntity);
        PublicationInfo publicationInfo;
        try {
            final Successful response = outgoingPublicationService.contactOperation(contactsEntity);
            publicationInfo = new PublicationInfo(PublicationStatusType.SUCCESS, request, XmlFormatter.format(response));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            publicationInfo = new PublicationInfo(PublicationStatusType.ERROR, request, e.getMessage());
        }

        return publicationInfo;
    }

}