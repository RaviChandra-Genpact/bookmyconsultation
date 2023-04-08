package com.upgrad.bookmyconsultation.enums;

public enum Speciality {
	CARDIOLOGIST, GENERAL_PHYSICIAN, DENTIST, PULMONOLOGIST, ENT, GASTRO;

	public static boolean contains(String enumValue) {
        try {
        	Speciality.valueOf(enumValue);
            return true;
        } catch (Exception e) {
            return false;
        }
	}
}

//CARDIOLOGIST|GENERAL_PHYSICIAN|DENTIST|PULMONOLOGIST|ENT|GASTRO
