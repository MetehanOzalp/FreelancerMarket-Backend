package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.Employer;
import GraduationProject.freelancermarket.model.dto.EmployerUpdateDto;
import GraduationProject.freelancermarket.repository.EmployerRepository;
import GraduationProject.freelancermarket.service.abstracts.EmployerService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployerManager implements EmployerService {

	private final EmployerRepository employerRepository;
	private final ModelMapper modelMapper;

	@Override
	public Result add(Employer employer) {
		employerRepository.save(employer);
		return new SuccessResult("İşveren eklendi");
	}

	@Override
	public Result delete(int id) {
		employerRepository.deleteById(id);
		return new SuccessResult("İşveren silindi");
	}

	@Override
	public Result update(EmployerUpdateDto employerUpdateDto) {
		Employer employer = modelMapper.map(employerUpdateDto, Employer.class);
		employerRepository.save(employer);
		return new SuccessResult("İşveren güncellendi");
	}

	@Override
	public DataResult<Employer> getById(int id) {
		return new SuccessDataResult<Employer>(employerRepository.findById(id).get(), "İşveren listelendi");
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(employerRepository.findAll(), "İşverenler listelendi");
	}

}
