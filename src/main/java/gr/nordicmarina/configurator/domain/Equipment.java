package gr.nordicmarina.configurator.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Equipment {

	private String equipmentType;
	private String refId;
	private String descriptionEn;
	private String descriptionEl;
	private int priceExclVatInCents;
	private int priceInclVatInCents;
	

	public Equipment(String equipmentType, String refId, String descriptionEn, String descriptionEl,
			int priceExclVatInCents, int priceInclVatInCents) {

		this.equipmentType = equipmentType;
		this.refId = refId;
		this.descriptionEn = descriptionEn;
		this.descriptionEl = descriptionEl;
		this.priceExclVatInCents = priceExclVatInCents;
		this.priceInclVatInCents = priceInclVatInCents;
	}
	public Equipment(Map<String, Object> map) {
		
		this.equipmentType = Objects.nonNull(map.get("equipmentType"))?map.get("equipmentType").toString():null;
		this.refId = Objects.nonNull(map.get("refId"))?map.get("refId").toString():null;
		this.descriptionEn = Objects.nonNull(map.get("descriptionEn"))?map.get("descriptionEn").toString():null;;
		this.descriptionEl = Objects.nonNull(map.get("descriptionEl"))?map.get("descriptionEl").toString():null;
		this.priceExclVatInCents = Objects.nonNull(map.get("priceExclVatInCents"))?Integer.valueOf(map.get("priceExclVatInCents").toString()):null;;
		this.priceInclVatInCents = Objects.nonNull(map.get("priceInclVatInCents"))?Integer.valueOf(map.get("priceInclVatInCents").toString()):null;;;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
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

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("equipmentType", equipmentType);
		map.put("descriptionEn", descriptionEn);
		map.put("descriptionEl", descriptionEn);
		map.put("refId", refId);
		map.put("priceExclVatInCents", priceExclVatInCents);
		map.put("priceInclVatInCents", priceInclVatInCents);
		return map;

	}
	
	@Override
	public String toString() {
		return "Equipment [equipmentType=" + equipmentType + ", refId=" + refId + ", descriptionEn=" + descriptionEn
				+ ", descriptionEl=" + descriptionEl + ", priceExclVatInCents=" + priceExclVatInCents
				+ ", priceInclVatInCents=" + priceInclVatInCents + "]";
	}
	
	
	

}
