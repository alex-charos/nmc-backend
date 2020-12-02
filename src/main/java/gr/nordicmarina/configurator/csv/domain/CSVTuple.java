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

	@JsonProperty("Internal Ref")
	private String internalRef;

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

	public String getInternalRef() {
		return internalRef;
	}

	public void setInternalRef(String internalRef) {
		this.internalRef = internalRef;
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
		return "CSVTuple [boatModel=" + boatModel + ", type=" + type + ", internalRef=" + internalRef
				+ ", descriptionEn=" + descriptionEn + ", descriptionEl=" + descriptionEl + ", priceExVat=" + priceExVat
				+ ", priceWithVat=" + priceWithVat + "]";
	}

	public Boat toBoat() throws ParseException {
		Integer d = Integer.parseInt(
				priceExVat.trim().substring(0, priceExVat.trim().indexOf(",") + 3).replace(",", "").replace(".", ""));
		Integer d2 = Integer.parseInt(priceWithVat.trim().substring(0, priceWithVat.trim().indexOf(",") + 3)
				.replace(",", "").replace(".", ""));

		return new Boat(null, boatModel, (d).intValue(), d2.intValue(), new ArrayList<Equipment>());
	}

	public Equipment toEquipment() throws ParseException {

		Integer d = Integer.valueOf(0);
		Integer d2 = Integer.valueOf(0);

		if (priceExVat != null && priceExVat.length() > 0) {
			if (priceExVat.trim().charAt(0) == '-') {
				
				priceExVat = priceExVat.trim().substring(1);
				if (priceExVat != null && priceExVat.length() > 0) {
				d = Integer.parseInt(priceExVat.trim().substring(0, priceExVat.trim().indexOf(",") + 3).replace(",", "")
						.replace(".", ""));
				d = d *-1;
				}
				
			} else {
				d = Integer.parseInt(priceExVat.trim().substring(0, priceExVat.trim().indexOf(",") + 3).replace(",", "")
						.replace(".", ""));
				
			}

		}

		if (priceWithVat != null && priceWithVat.length() > 0) {
			
			if (priceWithVat.trim().charAt(0) == '-') {
				priceWithVat = priceWithVat.trim().substring(1);
				if (priceWithVat != null && priceWithVat.length() > 0) {
				d2 = Integer.parseInt(priceWithVat.trim().substring(0, priceWithVat.trim().indexOf(",") + 3).replace(",", "")
						.replace(".", ""));
				d2 = d2 *-1;
				}
				
			} else {
				d2 = Integer.parseInt(priceWithVat.trim().substring(0, priceWithVat.trim().indexOf(",") + 3).replace(",", "")
						.replace(".", ""));
				
			}


		}

		return new Equipment(type, internalRef, descriptionEn, descriptionEl, d, d2);
	}

}
