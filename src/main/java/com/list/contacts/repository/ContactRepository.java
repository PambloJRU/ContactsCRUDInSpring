package com.list.contacts.repository;

import com.list.contacts.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
