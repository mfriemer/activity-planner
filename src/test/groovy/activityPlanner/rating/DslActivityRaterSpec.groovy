package activityPlanner.rating

import activityPlanner.weather.WeatherForecast
import spock.lang.Ignore
import spock.lang.Specification

class DslActivityRaterSpec extends Specification {
	def "Test rating resource found"() {
		File file = new File(getClass().getClassLoader().getResource("activityRatings.groovy").getFile())
				
		expect:
		file.getPath() != null
		file.getPath() != ""
		file.text != ""
		file.text.contains("activities")
	}
	
	def "Test parsing rating resource"() {
		File file = new File(getClass().getClassLoader().getResource("activityRatings.groovy").getFile())
				
		List<ActivityRating> ratings = DslActivityRater.parseRatings(file)
		
		expect:
		ratings.size() > 0
	}

	def "Test default rating resource"() {
		DslActivityRater rater = new DslActivityRater("activityRatings.groovy")
		
		WeatherForecast winterForecast = Mock(WeatherForecast)
		winterForecast.highTempF >> 30
		winterForecast.conditions >> "snow"
		
		WeatherForecast summerForecast = Mock(WeatherForecast)
		summerForecast.highTempF >> 85
		summerForecast.conditions >> "sunny"
		
		expect:
		rater.getActivitiesForForecast(winterForecast) != null
		rater.getActivitiesForForecast(summerForecast) != null
		rater.getActivitiesForForecast(winterForecast).any { it -> it.name == "skiing" }
		rater.getActivitiesForForecast(summerForecast).any { it -> it.name == "swimming" }
	}
}
