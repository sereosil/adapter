package ru.invitro.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.invitro.adapter.model.contact.ContactAttribute;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Component
@Repository
public interface ContactAttributeRepository extends JpaRepository<ContactAttribute, UUID> {


    Optional<List<ContactAttribute>> findAllByPackageId(String packageId);

}
