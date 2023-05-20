package com.ashu.police;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class PoliceService {

	@Autowired
	ComplaintsRepository complaintsRepository;

	// getting all complaints record by using the method findaAll() of CrudRepository
	public List<Complaints> getAllComplaints() {
		List<Complaints> complaints = new ArrayList<Complaints>();
		complaintsRepository.findAll().forEach(complaints1 -> complaints.add(complaints1));
		return complaints;
	}
	
	public void saveOrUpdate(Complaints complaint) {
		complaintsRepository.save(complaint);
	}
}
