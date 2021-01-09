package gr.nordicmarina.configurator.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Equipment {

	private String equipmentType;
	private String descriptionEn;
	private String descriptionEl;
	private List<String> detailsEn;
	private List<String> detailsEl;
	private int priceExclVatInCents;
	private int priceInclVatInCents;
	

	public Equipment(String equipmentType, String descriptionEn, String descriptionEl,
			int priceExclVatInCents, int priceInclVatInCents) {

		this.equipmentType = equipmentType;
		DescriptionAndDetails ddEn = initDescription(descriptionEn);
		DescriptionAndDetails ddEl = initDescription(descriptionEl);
		this.priceExclVatInCents = priceExclVatInCents;
		this.priceInclVatInCents = priceInclVatInCents;
		
		
		this.descriptionEl = ddEl.getDescription();
		this.detailsEl = ddEl.getDetails();
		this.descriptionEn = ddEn.getDescription();
		this.detailsEn = ddEn.getDetails();
		
		
	}
	public Equipment(Map<String, Object> map) {
		
		this.equipmentType = Objects.nonNull(map.get("equipmentType"))?map.get("equipmentType").toString():null;
		this.descriptionEn = Objects.nonNull(map.get("descriptionEn"))?map.get("descriptionEn").toString():null;;
		this.descriptionEl = Objects.nonNull(map.get("descriptionEl"))?map.get("descriptionEl").toString():null;
		this.priceExclVatInCents = Objects.nonNull(map.get("priceExclVatInCents"))?Integer.valueOf(map.get("priceExclVatInCents").toString()):null;;
		this.priceInclVatInCents = Objects.nonNull(map.get("priceInclVatInCents"))?Integer.valueOf(map.get("priceInclVatInCents").toString()):null;;;
	
		this.detailsEl = Objects.nonNull(map.get("detailsEl"))? (List<String>)map.get("detailsEl"):Collections.emptyList();;
		this.detailsEn = Objects.nonNull(map.get("detailsE"))?(List<String>)map.get("detailsEn"):Collections.emptyList();;
	
	
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
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

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("equipmentType", equipmentType);
		map.put("descriptionEn", descriptionEn);
		map.put("descriptionEl", descriptionEn);
		map.put("priceExclVatInCents", priceExclVatInCents);
		map.put("priceInclVatInCents", priceInclVatInCents);
		
		map.put("detailsEl", detailsEl);
		map.put("detailsEn", detailsEn);
		return map;

	}
	
	private DescriptionAndDetails initDescription(String desc) {
		DescriptionAndDetails d = null;
		if ( Objects.nonNull(desc) && desc.contains("\n") && desc.contains("-")) {
			List<String> split = Arrays.asList(desc.split("\r\n"));
			String descr  = split.get(0);
			List<String> dets = new ArrayList<>();
			List<String> ss = split.subList(1, split.size());
			for (String s : ss) {
				s = s.trim();
				if (s.startsWith("-")) {
					s = s.substring(1);
				}
				dets.add(s);
			}
			descr = descr.trim();
			if (descr.endsWith(":")) {
				descr = descr.substring(0, descr.length()-1).trim();
			}
			d = new DescriptionAndDetails(descr, dets);
		}
		else {
			d = new DescriptionAndDetails(desc, null);
		}
		return d;
	}
	

	
	
	
	@Override
	public String toString() {
		return "Equipment [equipmentType=" + equipmentType + ", descriptionEn=" + descriptionEn + ", descriptionEl="
				+ descriptionEl + ", detailsEn=" + detailsEn + ", detailsEl=" + detailsEl + ", priceExclVatInCents="
				+ priceExclVatInCents + ", priceInclVatInCents=" + priceInclVatInCents + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriptionEl == null) ? 0 : descriptionEl.hashCode());
		result = prime * result + ((descriptionEn == null) ? 0 : descriptionEn.hashCode());
		result = prime * result + ((detailsEl == null) ? 0 : detailsEl.hashCode());
		result = prime * result + ((detailsEn == null) ? 0 : detailsEn.hashCode());
		result = prime * result + ((equipmentType == null) ? 0 : equipmentType.hashCode());
		result = prime * result + priceExclVatInCents;
		result = prime * result + priceInclVatInCents;
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
		Equipment other = (Equipment) obj;
		if (descriptionEl == null) {
			if (other.descriptionEl != null)
				return false;
		} else if (!descriptionEl.equals(other.descriptionEl))
			return false;
		if (descriptionEn == null) {
			if (other.descriptionEn != null)
				return false;
		} else if (!descriptionEn.equals(other.descriptionEn))
			return false;
		if (detailsEl == null) {
			if (other.detailsEl != null)
				return false;
		} else if (!detailsEl.equals(other.detailsEl))
			return false;
		if (detailsEn == null) {
			if (other.detailsEn != null)
				return false;
		} else if (!detailsEn.equals(other.detailsEn))
			return false;
		if (equipmentType == null) {
			if (other.equipmentType != null)
				return false;
		} else if (!equipmentType.equals(other.equipmentType))
			return false;
		if (priceExclVatInCents != other.priceExclVatInCents)
			return false;
		if (priceInclVatInCents != other.priceInclVatInCents)
			return false;
		return true;
	}





	class DescriptionAndDetails {
		private final String description;
		private final List<String> details;
		public DescriptionAndDetails(String description, List<String> details) {
			super();
			this.description = description;
			this.details = details;
		}
		public String getDescription() {
			return description;
		}
		
		public List<String> getDetails() {
			return details;
		}

		
		
	}

}
