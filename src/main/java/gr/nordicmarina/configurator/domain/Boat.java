package gr.nordicmarina.configurator.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.cloud.firestore.QueryDocumentSnapshot;

public class Boat {
	private String id;
	private String model;
	private String refId;
	private String descriptionEn;
	private String descriptionEl;
	private int priceExclVatInCents;
	private int priceInclVatInCents;
	private List<Equipment> equipment;
	
	public Boat(String id, String model, int priceExclVatInCents, int priceInclVatInCents, List<Equipment> eq) {
		this.id = id;
		this.model = model;
		this.priceExclVatInCents = priceExclVatInCents;
		this.priceInclVatInCents = priceInclVatInCents;
		this.equipment = eq;
		
	}
	public Boat(QueryDocumentSnapshot s) {
		this(s.getData());
		this.id = s.getId();
	}
	public Boat(Map<String, Object> document) {
		this.id =  Objects.nonNull(document.get("id"))? document.get("id").toString():null;
		this.model = Objects.nonNull(document.get("model"))? document.get("model").toString():null;
		this.priceExclVatInCents = Objects.nonNull(document.get("priceExclVatInCents"))?Integer.valueOf(document.get("priceExclVatInCents").toString()):0;
		this.priceInclVatInCents = Objects.nonNull(document.get("priceInclVatInCents"))?Integer.valueOf(document.get("priceInclVatInCents").toString()):0;
		
		List<Map<String,Object>> ll = Objects.nonNull(document.get("equipment"))  ? (List<Map<String, Object>>) document.get("equipment") : Collections.emptyList();
		ll.stream().map(Equipment::new).collect(Collectors.toList());
 
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
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
	public int getPriceExclVatInCents() {
		return priceExclVatInCents;
	}
	public void setPriceExclVatInCents(int priceExclVatInCents) {
		this.priceExclVatInCents = priceExclVatInCents;
	}
	public int getPriceInclVatInCents() {
		return priceInclVatInCents;
	}
	public void setPriceInclVatInCents(int priceInclVatInCents) {
		this.priceInclVatInCents = priceInclVatInCents;
	}
	public List<Equipment> getEquipment() {
		return equipment;
	}
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);
		map.put("refId", refId);
		map.put("descriptionEn", descriptionEn);
		map.put("descriptionEl", descriptionEl);
		map.put("priceExclVatInCents", priceExclVatInCents);
		map.put("priceInclVatInCents", priceInclVatInCents);
		
		List<Map<String,Object>> eqs = equipment.stream().map(Equipment::toMap).collect(Collectors.toList());
		map.put("equipment", eqs);
		return map;
		
	}
	@Override
	public String toString() {
		return "Boat [id=" + id + ", model=" + model + ", refId=" + refId + ", descriptionEn=" + descriptionEn
				+ ", descriptionEl=" + descriptionEl + ", priceExclVatInCents=" + priceExclVatInCents
				+ ", priceInclVatInCents=" + priceInclVatInCents + ", equipment=" + equipment + "]";
	}
	
	
	 

}
