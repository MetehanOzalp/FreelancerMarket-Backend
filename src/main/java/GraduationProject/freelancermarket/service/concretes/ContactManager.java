package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.Contact;
import GraduationProject.freelancermarket.repository.ContactRepository;
import GraduationProject.freelancermarket.service.abstracts.ContactService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactManager implements ContactService {

	private final ContactRepository contactRepository;

	@Override
	public Result add(Contact contact) {
		contactRepository.save(contact);
		return new SuccessResult("Mesaj gönderildi");
	}

	@Override
	public DataResult<List<Contact>> getAll() {
		return new SuccessDataResult<List<Contact>>(contactRepository.findAll(), "Mesajlar listelendi");
	}

	@Override
	public Result changeStatus(int id) {
		Contact contact = contactRepository.findById(id).get();
		if (contact != null) {
			contact.setStatus(true);
			contactRepository.save(contact);
			return new SuccessResult("Mesaj durumu güncellendi");
		}
		return new ErrorResult("Mesaj bulunamadı");
	}

}
