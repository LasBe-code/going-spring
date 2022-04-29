package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Business;
import model.SearchDTO;
import repository.SearchDao;

@Service
public class SearchService {
	
	private final SearchDao searchDao;
	
	@Autowired
	public SearchService(SearchDao searchDao) {
		this.searchDao=searchDao;
	}
	
	public List<Business> searchBusinessList(SearchDTO searchDTO) throws Exception {
		return searchDao.searchBusinessList(searchDTO);
	}
	
	public List<Business> hot10BusinessList() throws Exception {
		return searchDao.hot10BusinessList();
	}
}
