package activityPlanner

import grails.converters.JSON
import grails.rest.RestfulController
import activityPlanner.weather.WeatherForecast
import activityPlanner.Activity;

import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import com.google.maps.model.LatLng

class ActivityController extends RestfulController {
	static responseFormats = ['json']
	
	def weatherLookup
	def geocodeContext
	def activityRater
	
	ActivityController() {
		super(Activity, true)
	}
	
	@Override
    def index() {
		List<Activity> activities = []
		double latitude
		double longitude
		
		if (params.latitude != null
				&& params.longitude != null) {
			latitude = params.latitude as Double
			longitude = params.longitude as Double
		}
		else if (params.postalCode != null) {
			LatLng latLng = geocode(params.postalCode)
			latitude = latLng.lat
			longitude = latLng.lng
		}
		else if (params.state != null && params.city != null) {
			LatLng latLng = geocode(params.state, params.city)
			latitude = latLng.lat
			longitude = latLng.lng
		}
		
		Date date = Calendar.getInstance().getTime()
		
		if (params.date != null) {
			//TODO: parse date
			activities.add(new Activity(id:7755, name:"football (North American)"))
		}
		
		WeatherForecast forecast = weatherLookup.getForecast(latitude, longitude, date)
				
		respond activityRater.getActivitiesForForecast(forecast)
	}
	
	@Override
	def show() {
		if (params.postalCode != null) {
			respond new Activity(id:params.id,  name:"biking")
		}
		else {
			respond new Activity(id:params.id,  name:"skiing")
		}
	}
	
	private LatLng geocode(String state, String city) {
		return geocode(city + ", " + state)
	}
	
	private LatLng geocode(String address) {
		//TODO: handle exceptions
		LatLng latLng
		GeocodingApi.newRequest(geocodeContext).address(address).await().each { GeocodingResult result ->
			if (result.geometry != null) {
				latLng = result.geometry.location
			}
		}
		
		return latLng
	}
}
