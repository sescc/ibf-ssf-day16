package ibf2023.ssf.day16;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2023.ssf.day16.model.Address;
import ibf2023.ssf.day16.model.Employee;
import ibf2023.ssf.day16.model.Phone;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@SpringBootApplication
public class Day16Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Day16Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Slide 7
		JsonObjectBuilder empBuilder = Json.createObjectBuilder();
		empBuilder.add("firstName", "Aces")
				.add("lastName", "Zynder")
				//.add("age", 25)
				.add("salary", 25000);

		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		addressBuilder.add("address1", "Ang Mo Kio")
				.add("address2", "#09-20")
				.add("postal", "753183");

		empBuilder.add("address", addressBuilder);

		JsonObjectBuilder phone1 = Json.createObjectBuilder();
		phone1.add("type", "mobile")
				.add("number", "93841347");

		JsonObjectBuilder phone2 = Json.createObjectBuilder();
		phone2.add("type", "work")
				.add("number", "63588433");

		JsonArrayBuilder phoneBuilder = Json.createArrayBuilder();
		phoneBuilder.add(phone1)
				.add(phone2);
		
		empBuilder.add("phoneNumber", phoneBuilder);

		JsonObject employee = empBuilder.build();		// *use the JsonObject under jakarta.json, not google
		System.out.println("Employee JsonObject: " + employee);

		// Getting data from JSON
		Employee emp = new Employee();
		emp.setFirstName(employee.get("firstName").toString());
		emp.setLastName(employee.getString("lastName"));
		emp.setSalary(Long.parseLong(employee.get("salary").toString()));

		// In order to get Address, need JSON object
		JsonObject addressObj = employee.getJsonObject("address");
		Address myAddress = new Address();
		myAddress.setLine1(addressObj.get("line1").toString());
		myAddress.setLine2(addressObj.get("line2").toString());
		myAddress.setPostal(addressObj.get("postal").toString());
		emp.setAddress(myAddress);

		// Use JSON object to read back phone number iteratively
		JsonArray phoneJsonArray = employee.getJsonArray("phoneNumber");
		List<Phone> phoneList = new ArrayList<>();

		for (int i = 0; i < phoneJsonArray.size(); i++) {
			JsonObject obj = phoneJsonArray.getJsonObject(i);
			Phone phone = new Phone(obj.getString("type"), obj.getString("number"));
			phoneList.add(phone);
		}
		emp.setPhoneNumbers(phoneList);

		System.out.println("Employee Entity: " + emp.toString());
	}

}
