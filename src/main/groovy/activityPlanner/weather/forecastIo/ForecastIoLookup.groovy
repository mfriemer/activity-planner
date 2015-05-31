package activityPlanner.weather.forecastIo

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date;
import java.util.List;
import org.springframework.web.client.RestTemplate

import activityPlanner.weather.WeatherForecast;
import activityPlanner.weather.WeatherLookup;

class ForecastIoLookup implements WeatherLookup {
	private static DateFormat FORECAST_IO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
	String key
	String url
	
	public ForecastIoLookup() {
	}
	
	@Override
	public WeatherForecast getForecast(double latitude, double longitude, Date date) {
		def location = url + "/" + key + "/" + latitude + "," + longitude + "," + FORECAST_IO_DATE_FORMAT.format(date)
		def resp = new RestTemplate().getForObject(location, ForecastIoForecast.class)
		//println resp.daily.data[0].temperatureMax
		
		return resp.getForecast()
	}

	@Override
	public List<WeatherForecast> getForecast(double latitude, double longitude, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return null
	}
}
