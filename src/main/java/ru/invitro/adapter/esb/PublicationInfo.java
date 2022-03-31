package ru.invitro.adapter.esb;

public class PublicationInfo {
    private PublicationStatusType status;
    final String request;
    final String response;

    public PublicationStatusType getStatus() {
        return this.status;
    }

    public String getRequest() {
        return this.request;
    }

    public String getResponse() {
        return this.response;
    }

    public void setStatus(PublicationStatusType status) {
        this.status = status;
    }

    public PublicationInfo(PublicationStatusType status, String request, String response) {
        this.status = status;
        this.request = request;
        this.response = response;
    }
}
