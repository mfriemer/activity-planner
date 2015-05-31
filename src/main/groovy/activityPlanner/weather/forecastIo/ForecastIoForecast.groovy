package activityPlanner.weather.forecastIo

import activityPlanner.weather.WeatherForecast

class ForecastIoForecast {
	double latitude
	double longitude
	ForecastIoDailyForecast daily
	
	public WeatherForecast getForecast() {
		WeatherForecast forecast = new WeatherForecast()
		def data = daily.data[0]
		forecast.conditions = data.summary
		forecast.date = data.time
		forecast.highTempF = data.temperatureMax
		forecast.lowTempF = data.temperatureMin
		//TODO wind, precip, etc		
		
		return forecast
	}
}
