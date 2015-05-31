package activityPlanner.rating

import activityPlanner.weather.WeatherForecast

class ActivityRating {
	String activityName
	Range<Double> tempRange
	List<String> excludeConditions = []
	Range<Double> windSpeedRange
	
	def static make(closure) {
		ActivityRating rating = new ActivityRating()
		closure.delegate = rating
		closure()
		return rating
	}
	
	def activity(String name) {
		activityName = name
	}
	
	def tempRange(double min, double max) {
		this.tempRange = new ObjectRange<Double>(min, max)
	}
	
	def windSpeedRange(double min, double max) {
		this.windSpeedRange = new ObjectRange<Double>(min, max)
	}
	
	def excludeConditions(String[] excludes) {
		this.excludeConditions.addAll(excludes)
	}
	
	boolean goodForForecast(WeatherForecast forecast) {
		boolean isGood = true
		isGood = (tempRange == null || tempRange.containsWithinBounds(forecast.highTempF))
		isGood = isGood && (windSpeedRange == null || windSpeedRange.containsWithinBounds(forecast.windSpeedMph))
		isGood = isGood && (excludeConditions.isEmpty() || !excludeConditions.any { it -> forecast.conditions.contains(it) })
		
		return isGood			
	}
}
