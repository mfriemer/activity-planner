package activityPlanner.rating

import activityPlanner.weather.WeatherForecast
import activityPlanner.Activity

class SimpleActivityRater implements ActivityRater {
	
	@Override
	List<Activity> getActivitiesForForecast(WeatherForecast forecast) {
		List<Activity> activities = []
		
		if (forecast.highTempF > 85) {
			activities.add(new Activity(id:1234, name:"swimming"))
		}
		else if (forecast.highTempF > 70) {
			activities.add(new Activity(id:456, name:"biking"))
		}
		else if (forecast.highTempF > 60) {
			activities.add(new Activity(id:789, name:"jogging"))
		}
		else if (forecast.highTempF < 30) {
			activities.add(new Activity(id:1122, name:"hockey"))
		}
		
		return activities
	}
}
