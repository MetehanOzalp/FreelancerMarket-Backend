package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
