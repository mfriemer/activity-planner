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
		
		WeatherForecast summerForecast = Mock(WeatherForecast)
		summerForecast.highTempF >> 85
		
		expect:
		rater.getActivitiesForForecast(winterForecast).any { it -> it.name == "skiing" }
		rater.getActivitiesForForecast(summerForecast).any { it -> it.name == "swimming" }
	}
}
