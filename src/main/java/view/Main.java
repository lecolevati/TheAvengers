package view;

import java.io.IOException;
import java.net.URISyntaxException;

import controller.service.AvengerService;
import dao.AvengerDaoImpl;

public class Main {

	public static void main(String[] args) {

		try {
			AvengerService avengerService = new AvengerService(new AvengerDaoImpl());
//			avengerService.getByGender("MALE").forEach(System.out::println);
			
//			Map<String, List<Avenger>> groupByGender = avengerService.groupByGender();
//			Set<String> getKeys = groupByGender.keySet();
//			for (String key : getKeys) {
//				groupByGender.get(key).forEach(System.out::println);
//				System.out.println("------------------------------------------------------------");
//			}
//			groupByGender.forEach((k, v) -> {
//				System.out.println(k);
//				v.forEach(System.out::println);
//			});
			
			avengerService.groupByYearFilterByGender("MALE")
							.forEach((k, v) -> {
								System.out.println(k);
								v.forEach(System.out::println);
							});	
			
			avengerService.countDeaths();
			System.out.println("---------------------------------------------------");
//			avengerService.getCountDeaths()
//							.entrySet()
//							.stream()
//							.sorted(Map.Entry.comparingByKey())
//							.forEach(System.out::println);

			avengerService.getCountDeaths().forEach((k, v) -> {
				System.out.print(k);
				System.out.println(v);
			});
			System.out.println("---------------------------------------------------");
//			avengerService.getCountRessurges()
//						.entrySet()
//						.stream()
//						.sorted(Map.Entry.comparingByKey())
//						.forEach(System.out::println);
			
			avengerService.getCountRessurges().forEach((k, v) -> {
				System.out.print(k);
				System.out.println(v);
			});

		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
