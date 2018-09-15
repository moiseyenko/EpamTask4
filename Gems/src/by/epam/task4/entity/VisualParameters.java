package by.epam.task4.entity;

import by.epam.task4.util.type.ColorType;

public class VisualParameters {

	private ColorType color;
	private String clarity;
	private int faces;

	public ColorType getColor() {
		return color;
	}

	public void setColor(ColorType value) {
		this.color = value;
	}

	public String getClarity() {
		return clarity;
	}

	public void setClarity(String value) {
		this.clarity = value;
	}

	public int getFaces() {
		return faces;
	}

	public void setFaces(int value) {
		this.faces = value;
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
		VisualParameters visualParameter = (VisualParameters) obj;
		if (color != visualParameter.color) {
			return false;
		}
		if (clarity == null) {
			if (visualParameter.clarity != null) {
				return false;
			}
		} else if (!clarity.equals(visualParameter.clarity)) {
			return false;
		}
		if (faces != visualParameter.faces) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((clarity == null) ? 0 : clarity.hashCode());
		result = prime * result + faces;
		return result;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": [color=" + color + ", clarity=" + clarity + ", faces=" + faces + "]";
	}

}
