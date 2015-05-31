package activityPlanner.rating

import activityPlanner.weather.WeatherForecast
import activityPlanner.Activity

interface ActivityRater {
	List<Activity> getActivitiesForForecast(WeatherForecast forecast)
}
