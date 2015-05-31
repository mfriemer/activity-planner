package activityPlanner.rating

import java.util.List;

import activityPlanner.Activity;
import activityPlanner.rating.ActivityRating;
import activityPlanner.weather.WeatherForecast;

class DslActivityRater implements ActivityRater {
	String ratingsDefPath
	List<ActivityRating> activities = []
	
	DslActivityRater() {
		
	}
	
	DslActivityRater(String ratingsDefPath) {
		this.ratingsDefPath = ratingsDefPath
		this.activities = parseRatings(new File(getClass().getClassLoader().getResource(ratingsDefPath).getFile()))
	}

	@Override
	public List<Activity> getActivitiesForForecast(WeatherForecast forecast) {
		if (activities.size() == 0) {
			activities = parseRatings(ratingsDefPath)
		}
		
		List<Activity> activitiesForDay = []
		activities.each { ActivityRating activity -> 
			if (activity.goodForForecast(forecast)) { 
				activitiesForDay.add(new Activity(name: activity.activityName))					
			} 
		}
		
		return activitiesForDay
	}
	
	static List<ActivityRating> parseRatings(String dslFilePath) {
		return parseRatings(new File(DslActivityRater.class.getClassLoader().getResource(dslFilePath).getFile()))
	}
	
	static List<ActivityRating> parseRatings(File dslFile) {
		List<ActivityRating> activities = []
		
		Script dslScript = new GroovyShell().parse(dslFile.text)
		
		dslScript.metaClass = createEMC(dslScript.class, {
			ExpandoMetaClass emc ->
			
			emc.activities = {
				Closure cl ->
				cl.delegate = new ActivityRatingDelegate(activities)
				cl.resolveStrategy = Closure.DELEGATE_FIRST
				
				cl()
			}
		})
		dslScript.run()		
		
		return activities
	}
	
	static ExpandoMetaClass createEMC(Class clazz, Closure cl) {
		ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
		
		cl(emc)
		
		emc.initialize()
		return emc
	}
}
