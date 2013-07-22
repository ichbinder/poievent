package de.htw_berlin.opentoken.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="PolygonPoiModel")
public class PolygonPoiModel extends PoiModel {
	
	private static final long serialVersionUID = -249477407937258847L;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="howIsTheCoordinateOfPolyPoi")
	private List<CoordinateModel> polygon;
	
	public PolygonPoiModel(String name, Set<TagModel> tags, UserModel createdBy, List<CoordinateModel> polygon) {
		this(name, createdBy);
		this.tags = tags;
		this.polygon = polygon;
	}
	
	public PolygonPoiModel(String name, UserModel createdBy) {
		this.name =name;
		this.createdBy = createdBy;
	}
	
	public PolygonPoiModel() {	
		super();
	}
	
	public List<CoordinateModel> getPolygon() {
		return polygon;
	}

	public void setPolygon(List<CoordinateModel> polygon) {
		this.polygon = polygon;
	}	
}
