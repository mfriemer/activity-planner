package activityPlanner.rating

import activityPlanner.weather.WeatherForecast
import spock.lang.Specification;

class ActivityRatingSpec extends Specification {
	def "Test rating"() {
		ActivityRating skiing = ActivityRating.make {
			activity "skiing"
			tempRange 0, 32
		}
		
		WeatherForecast winterForecast = Mock(WeatherForecast)
		winterForecast.highTempF >> 30
		
		WeatherForecast summerForecast = Mock(WeatherForecast)
		summerForecast.highTempF >> 85
		
		expect:
		skiing.goodForForecast(winterForecast) == true
		skiing.goodForForecast(summerForecast) == false
	}

}
