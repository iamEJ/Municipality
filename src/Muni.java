import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import lt.vtvpmc.municipality.IllegalCitizenException;
import lt.vtvpmc.municipality.Municipality;
import lt.vtvpmc.municipality.Person;

public class Muni implements Municipality{
	
	private Set<Person> persons = new HashSet<>();
	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	   LocalDate now = LocalDate.now();  

	@Override
	public Person findOldestPerson() {
		
		return  persons.stream().min(Comparator.comparing(Person::getDateOfBirth))
                .get() ;
	}

	@Override
	public int getCitizenCount() {
		return persons.size();
	}

	@Override
	public boolean isRegisteredCitizen(Person person) {
		if(persons.contains(person)) {
			return true;
		}
		return false;
	}

	@Override
	public void registerCitizen(Person person) throws IllegalCitizenException {
		
		if(person.getFirstName() == null || person.getFirstName().isEmpty()) {
			throw new IllegalCitizenException(person);
		}
		
		if(person.getLastName() == null || person.getLastName().isEmpty()) {
			throw new IllegalCitizenException(person);
		}
		
		if(person.getDateOfBirth() == null ) {
			throw new IllegalCitizenException(person);
		}
		
		if(person.getYearlyIncome() < 0 ) {
			throw new IllegalCitizenException(person);
		}
		
		if(person.getDateOfBirth().compareTo(now) > 0) {
			throw new IllegalCitizenException(person);
		}
		
		
		persons.add(person);
		
	}

	@Override
	public double totalIncomeInTaxes() {
		
		double total = 0;
		double tax15 = 0;
		double tax16 = 0;
		
		for(Person p :persons) {
			
			if(p.getYearlyIncome() > 1000000) {
				
				tax16 = tax16 + (p.getYearlyIncome() * 0.16); 
			}
			
			if(p.getYearlyIncome() > 14000 && p.getYearlyIncome() < 1000000) {
				tax15 = tax15 + (p.getYearlyIncome() * 0.15); 
			}
					
		}
		total = tax15 + tax16;
		return total;
	}

}
