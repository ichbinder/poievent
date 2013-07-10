package de.htw_berlin.opentoken.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.htw_berlin.opentoken.model.MessageModel;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long> {

}
