package salescloud.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomDataGenerator {

	private static final Faker faker = new Faker();
	private static final ObjectMapper objectMapper = new ObjectMapper();
	// Lists of possible values for ProductName and Region
	private static final List<String> productNames = List.of("Acer Veriton E220 Mini PC Intel Celeron Quad N5105",
			"Samsung Galaxy Tab A9+ 27.94 cm", "Croma Wired Keyboard", "HONOR Pad X8 25.65 cm (10.1 inch) FHD Display",
			"Tapo TP-Link C200 360° 2MP 1080p");

	private static final List<String> regions = List.of("Americas", "Asia Pacific", "Europe", "Middle East/Africa");
	 public static String generatePhoneNumber() {
	        // Generate a 10-digit number
	        long phoneNumber =  faker.number().numberBetween(1000000000, 9999999999L);

	        // Convert the number to a string
	        return String.valueOf(phoneNumber);
	    }

	
	

	public static File generateRandomData() {

		// Generate random data
		List<Map<String, String>> dataList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			// Select random ProductName and Region from lists
			String productName = productNames.get(faker.random().nextInt(productNames.size()));
			String region = regions.get(faker.random().nextInt(regions.size()));
			Map<String, String> data = new HashMap<>();
			data.put("ProductName", productName); // Use static ProductName
			data.put("FirstName", faker.name().firstName());
			data.put("LastName", faker.name().lastName());
			data.put("Company", faker.company().name());
			data.put("Phone", generatePhoneNumber());
			data.put("CompanyEmail", faker.internet().emailAddress());
			data.put("ContactEmail", faker.internet().emailAddress());
			data.put("Region", region); // Use static Region
			data.put("AnnualRevenue", String.valueOf(faker.number().numberBetween(10000, 1000000)));
			data.put("NumberOfEmployees", String.valueOf(faker.number().numberBetween(1, 1000)));
			data.put("Website", faker.internet().domainName());
			dataList.add(data);
		}

		// Write data to JSON file
		File file = new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\SubmitFormFieldsData.json");
		try {
			// Ensure the directory exists
	        Files.createDirectories(Paths.get(file.getParent()));
			objectMapper.writeValue(file, dataList);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}