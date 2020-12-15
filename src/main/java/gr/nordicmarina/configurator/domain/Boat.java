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
	private List<Equipment> equipment;
	
	public Boat(String id, String model, List<Equipment> eq) {
		this.id = id;
		this.model = model;
		this.equipment = eq;
		
	}
	public Boat(QueryDocumentSnapshot s) {
		this(s.getData());
		this.id = s.getId();
	}
	public Boat(Map<String, Object> document) {
		this.id =  Objects.nonNull(document.get("id"))? document.get("id").toString():null;
		this.model = Objects.nonNull(document.get("model"))? document.get("model").toString():null;
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
	
	public List<Equipment> getEquipment() {
		return equipment;
	}
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);
		
		List<Map<String,Object>> eqs = equipment.stream().map(Equipment::toMap).collect(Collectors.toList());
		map.put("equipment", eqs);
		return map;
		
	}
	@Override
	public String toString() {
		return "Boat [id=" + id + ", model=" + model + ", equipment=" + equipment + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boat other = (Boat) obj;
		if (equipment == null) {
			if (other.equipment != null)
				return false;
		} else if (!equipment.equals(other.equipment))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	
	
	 

}
