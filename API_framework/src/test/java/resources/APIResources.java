package resources;

public enum APIResources {
//	String addPlaceURL = "/maps/api/place/add/json";
//	String getPlaceURL = "/maps/api/place/get/json";
//	String deletePlaceURL = "/maps/api/place/get/json";
//
//	public String getaddPlaceURL() {
//		return addPlaceURL;
//	}
//
//	public String getgetPlaceURL() {
//		return getPlaceURL;
//	}
//
//	public String getdeletePlaceURL() {
//		return deletePlaceURL;
//	}

	AddPlaceURL("/maps/api/place/add/json"),
	GetPlaceURL("/maps/api/place/get/json"),
	DeletePlaceURL("/maps/api/place/delete/json");
	
	private String resource;
	
	APIResources(String resource) {
		this.resource=resource;
	}
	public String getResource() {
		return resource;
		
	}
	
}
