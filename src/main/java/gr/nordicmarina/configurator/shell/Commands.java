package gr.nordicmarina.configurator.shell;

import java.util.Arrays;
import java.util.List;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import gr.nordicmarina.configurator.domain.Boat;
import gr.nordicmarina.configurator.domain.Equipment;
import gr.nordicmarina.configurator.repository.BoatRepository;

@ShellComponent
public class Commands {

	private final BoatRepository boatRepository;

	public Commands(BoatRepository boatRepository) {
		this.boatRepository = boatRepository;
	}

	@ShellMethod("List all boats")
	public List<Boat> list() {
		return boatRepository.findAll();
	}

	@ShellMethod("Delete all boats (cannot undo!)")
	public void deleteAll() {
		boatRepository.deleteAll();
	}

	@ShellMethod("Add boat")
	public void add(@ShellOption(help = "Boat's model") String model,
			@ShellOption(help = "Models price (exclvat) (IN CENTS!)") int priceInCentsExvat, 
		@ShellOption(help = "Models price (incvat) (IN CENTS!)") int priceInCentsIncvat) {
		Equipment eq1 = new Equipment("test", "123", "test", "test", 123, 12354);
		boatRepository.add(new Boat(null, model, priceInCentsExvat,priceInCentsIncvat, Arrays.asList(eq1)));
	}

}
