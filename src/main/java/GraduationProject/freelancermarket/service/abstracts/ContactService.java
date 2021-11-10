package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Contact;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface ContactService {

	Result add(Contact contact);

	DataResult<List<Contact>> getAll();

	Result changeStatus(int id);

}
