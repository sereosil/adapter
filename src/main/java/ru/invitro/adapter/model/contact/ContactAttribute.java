package ru.invitro.adapter.model.contact;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.PostgresUUIDType;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "contact_attribute")
@Getter
@Setter
@Component
@NoArgsConstructor
@TypeDef(name="postgres-uuid",
        defaultForType = UUID.class,
        typeClass = PostgresUUIDType.class)
public class ContactAttribute implements Serializable {

    @Id
    @Column(name = "id")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "created_time")
    private ZonedDateTime createdTime;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    @Column(name = "contact")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID contact;

    @Column(name = "packageid")
    private String packageId;


    public ContactAttribute(UUID id, ZonedDateTime createdTime, String type, String value, UUID contact) {
        this.id = id;
        this.createdTime = createdTime;
        this.type = type;
        this.value = value;
        this.contact = contact;
    }
}
