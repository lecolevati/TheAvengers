package controller.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.AvengerDaoImpl;
import model.Avenger;

public class AvengerService {

	AvengerDaoImpl _aDao;
	
	public AvengerService(AvengerDaoImpl aDao) {
		this._aDao = aDao;
	}
	
	public List<Avenger> getByGender(String gender) throws URISyntaxException, IOException, InterruptedException {
		
		List<Avenger> avengers = _aDao.findAvengers();
		return avengers.stream()
						.filter(a -> a.getGender().equalsIgnoreCase(gender))
						.collect(Collectors.toList());
		
	}

	public Map<String, List<Avenger>> groupByGenderFilterByYear(int year) throws URISyntaxException, IOException, InterruptedException {
		
		List<Avenger> avengers = _aDao.findAvengers();
		return avengers.stream()
				.collect(Collectors.groupingBy(
						Avenger::getGender,
						Collectors.filtering(a -> a.getYear() > year,
						Collectors.toList())));
		
	}

	public Map<String, List<Avenger>> groupByGender() throws URISyntaxException, IOException, InterruptedException {
		
		List<Avenger> avengers = _aDao.findAvengers();
		return avengers.stream()
				.collect(Collectors.groupingBy(
						Avenger::getGender,
						Collectors.toList()));
		
	}

	public void countDeaths() throws URISyntaxException, IOException, InterruptedException {
		List<Avenger> avengers = _aDao.findAvengers();
		avengers.forEach(a -> {
			System.out.print(a.getName());
			System.out.print(" - ");
			System.out.print("Deaths :"+a.getDeaths().stream().filter(d -> d.getDeath() != null).filter(d -> d.getDeath() == true).count());
			System.out.print(" - ");
			System.out.println("Ressurges :"+a.getDeaths().stream().filter(d -> d.getRessurge() != null).filter(d -> d.getRessurge() == true).count());
			System.out.println("---------------------------------------X---------------------------------");
		});
	}

	public Map<String, Long> getCountDeaths() throws URISyntaxException, IOException, InterruptedException {
		List<Avenger> avengers = _aDao.findAvengers();
		
		return avengers.stream()
				.collect(
					Collectors.toMap(a -> a.getName() + " - Deaths:", a -> a.getDeaths().stream().filter(d -> d.getDeath() != null).filter(d -> d.getDeath() == true).count(), (k1, k2) -> k1));
	}

	public Map<String, Long> getCountRessurges() throws URISyntaxException, IOException, InterruptedException {
		List<Avenger> avengers = _aDao.findAvengers();
		return avengers.stream()
				
				.collect(
					Collectors.toMap(a -> a.getName() + " - Ressurges:", a -> a.getDeaths().stream().filter(d -> d.getRessurge() != null).filter(d -> d.getRessurge() == true).count(), (k1, k2) -> k1));
	}

}
