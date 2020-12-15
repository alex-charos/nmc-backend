package gr.nordicmarina.configurator.csv.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import gr.nordicmarina.configurator.domain.Boat;
import gr.nordicmarina.configurator.domain.Equipment;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CSVTuple {
	@JsonProperty("Boat model")
	private String boatModel;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Description English")
	private String descriptionEn;

	@JsonProperty("Description Greek")
	private String descriptionEl;

	@JsonProperty("Price ex VAT")
	private String priceExVat;

	@JsonProperty("Price with VAT")
	private String priceWithVat;

	public String getBoatModel() {
		return boatModel;
	}

	public void setBoatModel(String boatModel) {
		this.boatModel = boatModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionEl() {
		return descriptionEl;
	}

	public void setDescriptionEl(String descriptionEl) {
		this.descriptionEl = descriptionEl;
	}

	public String getPriceExVat() {
		return priceExVat;
	}

	public void setPriceExVat(String priceExVat) {
		this.priceExVat = priceExVat;
	}

	public String getPriceWithVat() {
		return priceWithVat;
	}

	public void setPriceWithVat(String priceWithVat) {
		this.priceWithVat = priceWithVat;
	}

	@Override
	public String toString() {
		return "CSVTuple [boatModel=" + boatModel + ", type=" + type + ", descriptionEn=" + descriptionEn
				+ ", descriptionEl=" + descriptionEl + ", priceExVat=" + priceExVat + ", priceWithVat=" + priceWithVat
				+ "]";
	}

	public Boat toBoat() throws ParseException {
		return new Boat(null, boatModel, new ArrayList<Equipment>());
	}

	public Equipment toEquipment() throws ParseException {

		Integer d = Integer.valueOf(0);
		Integer d2 = Integer.valueOf(0);

		if (priceExVat != null && priceExVat.length() > 0) {
			try {
				d = NumberFormat.getInstance(Locale.FRANCE).parse(priceExVat).intValue() * 100;
			} catch (Exception ex) {
				System.err.println("Could not parse: " + priceExVat);
				ex.printStackTrace();
			}

		}

		if (priceWithVat != null && priceWithVat.length() > 0) {
			try {
				d2 = NumberFormat.getInstance(Locale.FRANCE).parse(priceWithVat).intValue() * 100;
			} catch (Exception ex) {
				System.err.println("Could not parse: " + priceWithVat);
				ex.printStackTrace();
			}
		}

		return new Equipment(type, descriptionEn, descriptionEl, d, d2);
	}

}
