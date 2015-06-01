package activityPlanner

import grails.converters.JSON
import grails.rest.RestfulController
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletResponse
import activityPlanner.weather.WeatherForecast
import activityPlanner.Activity;

import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import com.google.maps.model.LatLng

class ActivityPlanController extends RestfulController {
	static responseFormats = ['json']
	
	static final DateFormat ISO_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
	
	def weatherLookup
	def geocodeContext
	def activityRater
	
	ActivityPlanController() {
		super(Activity, true)
	}
	
	@Override
    def index() {
		List<ActivityPlan> activities = []
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
		Date startDate = null
		Date endDate = null
		
		if (params.date != null) {
			//TODO: better incorrect format processing
			Date dateParam = parseDateParam(params.date)
			date = dateParam
		}
		
		if (params.startDate != null) {
			//TODO: better incorrect format processing
			Date dateParam = parseDateParam(params.startDate)
			startDate = dateParam
		}
		
		if (params.endDate != null) {
			//TODO: better incorrect format processing
			Date dateParam = parseDateParam(params.endDate)
			endDate = dateParam
		}
		
		if (startDate != null && endDate == null) {
			Calendar endCal = Calendar.getInstance()
			endCal.setTime(startDate)
			endCal.add(Calendar.DAY_OF_YEAR, 10)
			endDate = endCal.getTime()
		}
		
		if (endDate != null && startDate == null) {
			Calendar startCal = Calendar.getInstance()
			startCal.setTime(startDate)
			startCal.add(Calendar.DAY_OF_YEAR, -10)
			startDate = startCal.getTime()
		}
		
		if (endDate == null && startDate == null) {
			startDate = date
			endDate = date
		}		
		
		if (TimeUnit.MILLISECONDS.toDays(endDate.getTime() - startDate.getTime()) > 10) {
			respond response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Date range must be less than 10 days")
		}
		else if (TimeUnit.MILLISECONDS.toDays(endDate.getTime() - startDate.getTime()) < 0) {
			respond response.sendError(HttpServletResponse.SC_BAD_REQUEST, "End date must be after start date")
		}
		else {		
			Range<Date> dateRange = new ObjectRange<Date>(startDate, endDate)
			
			dateRange.each { it ->
				activities.add(new ActivityPlan(date: it, activities : activityRater.getActivitiesForForecast(weatherLookup.getForecast(latitude, longitude, it))))
			}
					
			respond activities
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
	
	private Date parseDateParam(String dateString) {
		Date dateParam
		if (dateString.matches(/\d+/)) {
			dateParam = new Date(Long.parseLong(dateString))
		}
		else {
			dateParam = ISO_FORMAT.parse(dateString)
		}
		
		return dateParam
	}
}
