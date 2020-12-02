package gr.nordicmarina.configurator.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import gr.nordicmarina.configurator.config.FirebaseConfiguration;
import gr.nordicmarina.configurator.csv.domain.CSVTuple;
import gr.nordicmarina.configurator.domain.Boat;
import gr.nordicmarina.configurator.domain.Equipment;

public class CSVRepositoryTest {
	
	

	@Test
	public void test() throws FileNotFoundException, IOException {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');

		ObjectReader oReader = csvMapper.reader(CSVTuple.class).with(schema);
		List<CSVTuple> tt = new ArrayList<CSVTuple>();
		try (Reader reader = new InputStreamReader(
				this.getClass().getClassLoader().getResourceAsStream("pricingweb.csv"))) {
			MappingIterator<CSVTuple> mi = oReader.readValues(reader);
			while (mi.hasNext()) {
				CSVTuple n = (mi.next());
				
				tt.add(n);
			}
		}

		List<Boat> boats = parse(tt);
		
		boats.stream().forEach(System.out::println);
		
BoatRepository repo = new FirebaseBoatRepository(new FirebaseConfiguration().fireStore("firebase.json"));
		
		repo.deleteAll();
		
		boats.stream().forEach(repo::add);
		
		repo.findAll();
		
	}

	private List<Boat> parse(List<CSVTuple> tuples) {
		List<Boat> boats = tuples.stream().filter(p -> "Boat".equals(p.getType())).map(arg0 -> {
			try {
				return arg0.toBoat();
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
		
		tuples.stream().filter(p -> !"Boat".equals(p.getType())).forEach(p-> {
			Boat b = getBoat(p.getBoatModel(), boats);
			try {
				b.getEquipment().add(p.toEquipment());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return boats;

	}
	
	private Boat getBoat(String model, List<Boat> boats) {
		return boats.stream().filter(p->p.getModel().equals(model)).findFirst().get();
	}

}
