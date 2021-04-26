package com;

/* This class represents the lower and upper bounds of a zip code range where each value is a 5 digit Integer
 * If the upper bound is smaller than the lower bound it will inverse the values.
 */

public class ZipCodePair {

	private Integer zipA;
	private Integer zipB;
	
	public ZipCodePair(Integer zipA,Integer zipB){
		if (zipA<zipB){
			this.zipA=zipA;
			this.zipB=zipB;
		} else{
			this.zipA=zipB;
			this.zipB=zipA;			
		}
	}

	public Integer getZipA() {
		return zipA;
	}

	public void setZipA(Integer zipA) {
		this.zipA = zipA;
	}

	public Integer getZipB() {
		return zipB;
	}

	public void setZipB(Integer zipB) {
		this.zipB = zipB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zipA == null) ? 0 : zipA.hashCode());
		result = prime * result + ((zipB == null) ? 0 : zipB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ZipCodePair other = (ZipCodePair) obj;
		if (zipA == null) {
			if (other.zipA != null)
				return false;
		} else if (!zipA.equals(other.zipA))
			return false;
		if (zipB == null) {
			if (other.zipB != null) {
				return false;
			}
		} else if (!zipB.equals(other.zipB)) {
			return false;
		}
		return true;
	}
	 
	/*
     * Returns a string representation of this object in [zipA, ZipB] format.
     * @return String representation of this Pair object
     */
	@Override
	public String toString() {
		return String.format("['%d','%d']", zipA, zipB );
	}
}
