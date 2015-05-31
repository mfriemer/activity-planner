import activityPlanner.weather.WeatherLookup
import activityPlanner.weather.forecastIo.ForecastIoLookup
import com.google.maps.GeoApiContext
import activityPlanner.rating.DslActivityRater

// Place your Spring DSL code here
beans = {	
	weatherLookup(ForecastIoLookup) {
		url = '${weatherLookup.url}'
		key = '${weatherLookup.key}'
	}
	
	geocodeContext(GeoApiContext) {
		apiKey = '${geocode.key}'
	}
	
	activityRater(DslActivityRater) {
		ratingsDefPath = '${ratings.defPath}'
	}
}
