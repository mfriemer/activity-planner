import activityPlanner.weather.WeatherLookup
import activityPlanner.weather.forecastIo.ForecastIoLookup
import com.google.maps.GeoApiContext
import activityPlanner.rating.DslActivityRater

// Place your Spring DSL code here
beans = {
//	weatherLookup(WundergroundLookup) {
//		url = "http://api.wunderground.com/api"
//		key = "d57366b8724777f8"
//	}
	
	weatherLookup(ForecastIoLookup) {
		url = "https://api.forecast.io/forecast"
		key = "bac6f80455dabd1c57e0f077fa758490"
	}
	
	geocodeContext(GeoApiContext) {
		apiKey = "AIzaSyApIN3MGCAkKbLTRhCS-cfxruV9cZiJUjE"
	}
	
	activityRater(DslActivityRater) {
		ratingsDefPath = "activityRatings.groovy"
	}
}
