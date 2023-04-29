package ir.encoding.order.types.product;

import lombok.Getter;

public enum MeasurementType {

	QUANTITY("QUANTITY"),
	KILOGRAM("KILOGRAM"),
	GRAP("GRAM"),
	MILLIGRAM("MILLIGRAM"),
	KILOLITRE("KILOLITRE"),
	LITER("LITER"),
	MILIILITER("MILILITER"),
	KILOMETER("KILOMETER"),
	METER("METER"),
	CENTIMETER("CENTIMETER"),
	MILLIMETER("MILLIMETER"),	
	;		
	
	@Getter
	private String value;
	
	private MeasurementType(String value) {
		this.value = value;
	}
}
