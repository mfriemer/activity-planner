package activityPlanner.weather

interface WeatherLookup {
	public WeatherForecast getForecast(double latitude, double longitude, Date date)
	public List<WeatherForecast> getForecast(double latitude, double longitude, Date startDate, Date endDate)
}
